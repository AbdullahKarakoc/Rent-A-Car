    package io.reflectoring.rentAcar.service;

    import io.reflectoring.rentAcar.auth._auth.UserService;
    import io.reflectoring.rentAcar.auth.user.User;
    import io.reflectoring.rentAcar.auth.user.UserRepository;
    import io.reflectoring.rentAcar.auth.user.UserResponseDto;
    import io.reflectoring.rentAcar.domain.model.*;
    import io.reflectoring.rentAcar.domain.request.RentalsRequestDto;
    import io.reflectoring.rentAcar.domain.response.CarsResponseDto;
    import io.reflectoring.rentAcar.domain.response.PaymentsResponseDto;
    import io.reflectoring.rentAcar.domain.response.RentalsResponseDto;
    import io.reflectoring.rentAcar.domain.response.StaffsResponseDto;
    import io.reflectoring.rentAcar.exception.CarNotAvailableException;
    import io.reflectoring.rentAcar.exception.DataNotFoundException;
    import io.reflectoring.rentAcar.repository.*;
    import jakarta.transaction.Transactional;
    import lombok.RequiredArgsConstructor;
    import org.modelmapper.ModelMapper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.scheduling.annotation.Scheduled;
    import org.springframework.stereotype.Service;

    import java.time.LocalDateTime;
    import java.util.List;
    import java.util.UUID;
    import java.util.stream.Collectors;


    @Service
    @RequiredArgsConstructor
    public class RentalsService {

        private final RentalsRepository rentalRepository;
        private final CarsService carService;
        private final UserService userService;
        private final StaffService staffService;
        private final PaymentsService paymentService;
        private final ModelMapper modelMapper;


        public List<RentalsResponseDto> getAllRentals() {
            List<Rentals> rentals = rentalRepository.findAll();
            return rentals.stream()
                    .map(rental -> modelMapper.map(rental, RentalsResponseDto.class))
                    .collect(Collectors.toList());
        }

        public RentalsResponseDto getRentalById(UUID id) {
            Rentals rental = findById(id);
            return modelMapper.map(rental, RentalsResponseDto.class);
        }

        @Transactional
        public RentalsResponseDto saveRental(RentalsRequestDto rentalRequestDto) {
            // Fetch car, staff, user and payment details using their respective services
            Cars car = carService.findById(rentalRequestDto.getCarUUID());

            // Car availability check
            if (!car.isAvailable()) {
                throw new CarNotAvailableException("This car is already rented until the return date. Please choose another car.");
            }

            Staffs staff = staffService.findById(rentalRequestDto.getStaffUUID());
            User user = userService.findById(rentalRequestDto.getUserUUID());

            // Save payment and get the payment model
            Payments payment = modelMapper.map(rentalRequestDto.getPayment(), Payments.class);
            Payments savedPayment = paymentService.save(payment);

            // Map the request DTO to the rental model
            Rentals rental = modelMapper.map(rentalRequestDto, Rentals.class);
            rental.setCar(car);
            rental.setStaff(staff);
            rental.setUser(user);
            rental.setPayment(savedPayment);

            // Set car as unavailable
            carService.setCarUnavailable(car);

            // Save the rental and map to response DTO
            Rentals savedRental = save(rental);
            return modelMapper.map(savedRental, RentalsResponseDto.class);
        }

        @Transactional
        public RentalsResponseDto updateRental(UUID id, RentalsRequestDto rentalRequestDto) {
            Rentals existingRental = findById(id);

            Cars car = carService.findById(rentalRequestDto.getCarUUID());

            Staffs staff = staffService.findById(rentalRequestDto.getStaffUUID());

            User user = userService.findById(rentalRequestDto.getUserUUID());


            Payments payment = modelMapper.map(rentalRequestDto.getPayment(), Payments.class);
            Payments savedPayment = paymentService.save(payment);

            modelMapper.map(rentalRequestDto, existingRental);
            existingRental.setCar(car);
            existingRental.setStaff(staff);
            existingRental.setUser(user);
            existingRental.setPayment(savedPayment);

            Rentals updatedRental = save(existingRental);
            return modelMapper.map(updatedRental, RentalsResponseDto.class);
        }

        public void deleteRental(UUID id) {
            Rentals rental = findById(id);
            rentalRepository.delete(rental);
        }

        @Scheduled(cron = "0 0 0 * * ?") // Runs every day at midnight
        public void updateCarAvailability() {
            List<Rentals> rentals = rentalRepository.findAll();
            LocalDateTime now = LocalDateTime.now();

            for (Rentals rental : rentals) {
                if (rental.getReturnDate().isBefore(now)) {
                    Cars car = rental.getCar();
                    carService.setCarAvailable(car);
                }
            }
        }

        public Rentals save(Rentals rental) {
            return rentalRepository.saveAndFlush(rental);
        }

        public Rentals findById(UUID id) {
            return rentalRepository.findById(id)
                    .orElseThrow(() -> new DataNotFoundException("Rental not found"));
        }


        public List<Rentals> getRentalsByUser(User user) {
            return rentalRepository.findAll().stream()
                    .filter(rental -> rental.getUser().equals(user))
                    .collect(Collectors.toList());
        }

    }



