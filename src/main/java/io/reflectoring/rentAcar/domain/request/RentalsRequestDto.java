package io.reflectoring.rentAcar.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


@Data
public class RentalsRequestDto {

    @NotNull(message = "Rental date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime rentalDate;

    @NotNull(message = "Return date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime returnDate;


    @NotNull(message = "Car ID is required")
    private UUID carUUID;

    @NotNull(message = "Staff ID is required")
    private UUID staffUUID;

    @NotNull(message = "Customer information is required")
    private CustomersRequestDto customer;

    @NotNull(message = "Payment information is required")
    private PaymentsRequestDto payment;

}
