package io.reflectoring.rentAcar.domain.request;

import io.reflectoring.rentAcar.enums.PaymentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PaymentsRequestDto {


    @NotNull(message = "Amount is required")
    private int totalAmount;

    @NotNull(message = "Payment type is required")
    private PaymentType paymentType;
}
