package io.reflectoring.rentAcar.service;

import io.reflectoring.rentAcar.auth.user.User;
import io.reflectoring.rentAcar.auth.user.UserRepository;
import io.reflectoring.rentAcar.domain.model.*;
import io.reflectoring.rentAcar.domain.request.RentalsRequestDto;
import io.reflectoring.rentAcar.domain.response.RentalsResponseDto;
import io.reflectoring.rentAcar.exception.CarNotAvailableException;
import io.reflectoring.rentAcar.exception.DataNotFoundException;
import io.reflectoring.rentAcar.repository.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class RentalsService {

    @Autowired
    private RentalsRepository rentalRepository;

    @Autowired
    private CarsRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<RentalsResponseDto> getAllRentals() {
        List<Rentals> rentals = rentalRepository.findAll();
        return rentals.stream()
                .map(rental -> modelMapper.map(rental, RentalsResponseDto.class))
                .collect(Collectors.toList());
    }

    public RentalsResponseDto getRentalById(UUID id) {
        Rentals rental = rentalRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Rental not found"));
        return modelMapper.map(rental, RentalsResponseDto.class);
    }

    @Transactional
    public RentalsResponseDto saveRental(RentalsRequestDto rentalRequestDto) {
        Cars car = carRepository.findById(rentalRequestDto.getCarUUID())
                .orElseThrow(() -> new DataNotFoundException("Car not found"));

        // Car availability check
        if (!car.isAvailable()) {
            throw new CarNotAvailableException("This car is already rented until the return date. Please choose another car.");
        }

        Staffs staff = staffRepository.findById(rentalRequestDto.getStaffUUID())
                .orElseThrow(() -> new DataNotFoundException("Staff not found"));

        User user = userRepository.findById(rentalRequestDto.getUserUUID())
                .orElseThrow(() -> new DataNotFoundException("Customer not found"));


        Payments payment = modelMapper.map(rentalRequestDto.getPayment(), Payments.class);
        payment = paymentsRepository.save(payment);

        Rentals rental = modelMapper.map(rentalRequestDto, Rentals.class);
        rental.setCar(car);
        rental.setStaff(staff);
        rental.setUser(user);
        rental.setPayment(payment);

        // Set car as unavailable
        car.setAvailable(false);
        carRepository.save(car);

        rental = rentalRepository.save(rental);
        return modelMapper.map(rental, RentalsResponseDto.class);
    }

    @Transactional
    public RentalsResponseDto updateRental(UUID id, RentalsRequestDto rentalRequestDto) {
        Rentals existingRental = rentalRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Rental not found"));

        Cars car = carRepository.findById(rentalRequestDto.getCarUUID())
                .orElseThrow(() -> new DataNotFoundException("Car not found"));

        Staffs staff = staffRepository.findById(rentalRequestDto.getStaffUUID())
                .orElseThrow(() -> new DataNotFoundException("Staff not found"));

        User user = userRepository.findById(rentalRequestDto.getUserUUID())
                .orElseThrow(() -> new DataNotFoundException("Customer not found"));


        Payments payment = modelMapper.map(rentalRequestDto.getPayment(), Payments.class);
        payment = paymentsRepository.save(payment);

        modelMapper.map(rentalRequestDto, existingRental);
        existingRental.setCar(car);
        existingRental.setStaff(staff);
        existingRental.setUser(user);
        existingRental.setPayment(payment);

        Rentals updatedRental = rentalRepository.save(existingRental);
        return modelMapper.map(updatedRental, RentalsResponseDto.class);
    }

    public void deleteRental(UUID id) {
        Rentals rental = rentalRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Rental not found"));
        rentalRepository.delete(rental);
    }

    @Scheduled(cron = "0 0 0 * * ?") // Runs every day at midnight
    public void updateCarAvailability() {
        List<Rentals> rentals = rentalRepository.findAll();
        LocalDateTime now = LocalDateTime.now();

        for (Rentals rental : rentals) {
            if (rental.getReturnDate().isBefore(now)) {
                Cars car = rental.getCar();
                car.setAvailable(true);
                carRepository.save(car);
            }
        }
    }
}

