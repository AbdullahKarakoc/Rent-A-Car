package io.reflectoring.rentAcar.auth._auth_customer.customer;

import io.reflectoring.rentAcar.domain.response.RentalsResponseDto;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CustomersResponseDto {
    private UUID customerUUID;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private String phone;
    private String email;
    private String licenseNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private RentalsResponseDto rentals;

}
