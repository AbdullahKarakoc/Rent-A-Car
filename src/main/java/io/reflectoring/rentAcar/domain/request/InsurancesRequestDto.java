package io.reflectoring.rentAcar.domain.request;

import io.reflectoring.rentAcar.enums.InsuranceCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class InsurancesRequestDto {

    @NotNull(message = "Car ID is required")
    private UUID carUUID;

    @NotBlank(message = "Provider is required")
    private String provider;

    @NotNull(message = "Amount is required")
    private double amount;

    @NotBlank(message = "Insurance category is required")
    private String insuranceCategory;
}
