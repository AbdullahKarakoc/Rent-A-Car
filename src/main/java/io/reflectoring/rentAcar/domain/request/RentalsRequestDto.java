package io.reflectoring.rentAcar.domain.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RentalsRequestDto {
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
}
