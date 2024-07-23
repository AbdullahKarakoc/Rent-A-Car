package io.reflectoring.rentAcar.domain.response;

import io.reflectoring.rentAcar.auth.user.UserResponseDto;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RentalsResponseDto {
    private UUID rentalUUID;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime rentalDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime returnDate;
    private CarsResponseDto car;
    private StaffsResponseDto staff;
    private PaymentsResponseDto payment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}
