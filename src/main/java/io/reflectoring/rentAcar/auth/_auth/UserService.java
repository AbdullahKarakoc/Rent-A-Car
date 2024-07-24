package io.reflectoring.rentAcar.auth._auth;

import io.reflectoring.rentAcar.auth.user.User;
import io.reflectoring.rentAcar.auth.user.UserRepository;
import io.reflectoring.rentAcar.auth.user.UserResponseDto;
import io.reflectoring.rentAcar.domain.model.Rentals;
import io.reflectoring.rentAcar.domain.response.CarsResponseDto;
import io.reflectoring.rentAcar.domain.response.RentalsResponseDto;
import io.reflectoring.rentAcar.exception.DataNotFoundException;
import io.reflectoring.rentAcar.repository.RentalsRepository;
import io.reflectoring.rentAcar.service.RentalsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserService {
    private final UserRepository userRepository;

    private final RentalsService rentalsService;

    private final ModelMapper modelMapper;

    private UserService(
            UserRepository userRepository,
            @Lazy RentalsService rentalsService,
            ModelMapper modelMapper){

        this.userRepository = userRepository;
        this.rentalsService = rentalsService;
        this.modelMapper = modelMapper;
    }


    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }

    public UserResponseDto getUserById(UUID id) {
        User user = findById(id);
        return modelMapper.map(user, UserResponseDto.class);

    }

    public UserResponseDto updateUser(UUID id, AuthenticationRequest authenticationRequest) {
        User existingUser = findById(id);
        modelMapper.map(authenticationRequest, existingUser);
        User updatedUser = save(existingUser);

        UserResponseDto userDto = modelMapper.map(updatedUser, UserResponseDto.class);
        List<Rentals> rentals = rentalsService.getRentalsByUser(updatedUser);
        List<RentalsResponseDto> rentalDtos = rentals.stream()
                .map(rental -> modelMapper.map(rental, RentalsResponseDto.class))
                .collect(Collectors.toList());
        userDto.setRentals(rentalDtos);

        return userDto;
    }

    public void deleteUser(UUID id) {
        User user = findById(id);
        user.setDeleted(true);
        save(user);
    }

    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found"));
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}



