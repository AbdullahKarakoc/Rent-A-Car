package io.reflectoring.rentAcar.service;

import io.reflectoring.rentAcar.domain.model.Cars;
import io.reflectoring.rentAcar.domain.model.Customers;
import io.reflectoring.rentAcar.domain.model.Rentals;
import io.reflectoring.rentAcar.domain.request.RentalsRequestDto;
import io.reflectoring.rentAcar.domain.response.RentalsResponseDto;
import io.reflectoring.rentAcar.exception.DataNotFoundException;
import io.reflectoring.rentAcar.repository.CarsRepository;
import io.reflectoring.rentAcar.repository.CustomersRepository;
import io.reflectoring.rentAcar.repository.RentalsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private CustomersRepository customerRepository;

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

    public RentalsResponseDto saveRental(RentalsRequestDto rentalRequestDto) {
        Cars car = carRepository.findById(rentalRequestDto.getCarUUID())
                .orElseThrow(() -> new DataNotFoundException("Car not found"));

        Customers customer = customerRepository.findById(rentalRequestDto.getCustomerUUID())
                .orElseThrow(() -> new DataNotFoundException("Customer not found"));

        Rentals rental = modelMapper.map(rentalRequestDto, Rentals.class);
        rental.setCar(car);
        rental.setCustomer(customer);
        rental = rentalRepository.save(rental);
        return modelMapper.map(rental, RentalsResponseDto.class);
    }

    public RentalsResponseDto updateRental(UUID id, RentalsRequestDto rentalRequestDto) {
        Rentals existingRental = rentalRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Rental not found"));

        Cars car = carRepository.findById(rentalRequestDto.getCarUUID())
                .orElseThrow(() -> new DataNotFoundException("Car not found"));

        Customers customer = customerRepository.findById(rentalRequestDto.getCustomerUUID())
                .orElseThrow(() -> new DataNotFoundException("Customer not found"));

        modelMapper.map(rentalRequestDto, existingRental);
        existingRental.setCar(car);
        existingRental.setCustomer(customer);
        Rentals updatedRental = rentalRepository.save(existingRental);
        return modelMapper.map(updatedRental, RentalsResponseDto.class);
    }

    public void deleteRental(UUID id) {
        Rentals rental = rentalRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Rental not found"));
        rentalRepository.delete(rental);
    }
}
