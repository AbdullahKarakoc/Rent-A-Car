package io.reflectoring.rentAcar.auth._auth;

import io.reflectoring.rentAcar.auth.user.User;
import io.reflectoring.rentAcar.auth.user.UserRepository;
import io.reflectoring.rentAcar.auth.user.UserResponseDto;
import io.reflectoring.rentAcar.domain.model.Rentals;
import io.reflectoring.rentAcar.domain.response.RentalsResponseDto;
import io.reflectoring.rentAcar.exception.DataNotFoundException;
import io.reflectoring.rentAcar.repository.RentalsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentalsRepository rentalsRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> {
                    UserResponseDto userDto = modelMapper.map(user, UserResponseDto.class);
                    List<Rentals> rentals = rentalsRepository.findByUser_UserUUID(user.getUserUUID());
                    List<RentalsResponseDto> rentalDtos = rentals.stream()
                            .map(rental -> modelMapper.map(rental, RentalsResponseDto.class))
                            .collect(Collectors.toList());
                    userDto.setRentals(rentalDtos);
                    return userDto;
                })
                .collect(Collectors.toList());
    }

    public UserResponseDto getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        UserResponseDto userDto = modelMapper.map(user, UserResponseDto.class);
        List<Rentals> rentals = rentalsRepository.findByUser_UserUUID(user.getUserUUID());
        List<RentalsResponseDto> rentalDtos = rentals.stream()
                .map(rental -> modelMapper.map(rental, RentalsResponseDto.class))
                .collect(Collectors.toList());
        userDto.setRentals(rentalDtos);
        return userDto;
    }

    public UserResponseDto updateUser(UUID id, AuthenticationRequest authenticationRequest) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        modelMapper.map(authenticationRequest, existingUser);
        User updatedUser = userRepository.save(existingUser);

        UserResponseDto userDto = modelMapper.map(updatedUser, UserResponseDto.class);
        List<Rentals> rentals = rentalsRepository.findByUser_UserUUID(updatedUser.getUserUUID());
        List<RentalsResponseDto> rentalDtos = rentals.stream()
                .map(rental -> modelMapper.map(rental, RentalsResponseDto.class))
                .collect(Collectors.toList());
        userDto.setRentals(rentalDtos);

        return userDto;
    }

    public void deleteUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        userRepository.delete(user);
    }
}
