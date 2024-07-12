package io.reflectoring.rentAcar.domain.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RentalsResponseDto {
    private UUID rentalUUID;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

}
