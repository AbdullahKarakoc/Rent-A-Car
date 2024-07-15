package io.reflectoring.rentAcar.domain.response;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
public class RentalsResponseDto {
    private UUID rentalUUID;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rentalDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;
    private CarsResponseDto car;
    private StaffsResponseDto staff;
    private CustomersResponseDto customer;
    private PaymentsResponseDto payment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}
