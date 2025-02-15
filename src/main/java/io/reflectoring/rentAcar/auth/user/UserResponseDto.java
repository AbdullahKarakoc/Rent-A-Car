package io.reflectoring.rentAcar.auth.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.reflectoring.rentAcar.domain.response.RentalsResponseDto;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class UserResponseDto {

    private UUID userUUID;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    @Column(unique = true)
    private String email;
    private List<RentalsResponseDto> rentals;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
