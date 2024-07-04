package io.reflectoring.rentAcar.domain.request;

import lombok.Data;

@Data
public class PaymentsRequestDto {
    private String cardNumber;
    private double amount;
    private String paymentType;
}
