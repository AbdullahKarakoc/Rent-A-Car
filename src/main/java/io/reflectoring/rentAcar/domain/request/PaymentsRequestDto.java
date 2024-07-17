package io.reflectoring.rentAcar.domain.request;

import io.reflectoring.rentAcar.enums.PaymentType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PaymentsRequestDto {


    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Amount must be at least 1")
    @Max(value = 1000000, message = "Amount must be less than or equal to 10000")
    private int totalAmount;

    @NotNull(message = "Payment type is required")
    private PaymentType paymentType;
}
