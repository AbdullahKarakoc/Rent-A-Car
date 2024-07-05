package io.reflectoring.rentAcar.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


@Data
public class RentalsRequestDto {

    @NotNull(message = "Car ID is required")
    private UUID carUUID;

    @NotNull(message = "Customer ID is required")
    private UUID customerUUID;

    @NotNull(message = "Rental date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rentalDate;

    @NotNull(message = "Return date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;
}
