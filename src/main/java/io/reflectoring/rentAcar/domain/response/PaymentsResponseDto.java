package io.reflectoring.rentAcar.domain.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PaymentsResponseDto {
    private UUID paymentUUID;
    private String cardNumber;
    private double amount;
    private String paymentType;

}
