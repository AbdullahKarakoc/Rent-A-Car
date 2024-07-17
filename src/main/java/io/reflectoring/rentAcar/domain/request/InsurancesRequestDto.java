package io.reflectoring.rentAcar.domain.request;

import io.reflectoring.rentAcar.enums.InsuranceCategoryType;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class InsurancesRequestDto {


    @NotBlank(message = "Provider is required")
    @Size(min = 1, max = 30, message = "Provider name must be between 1 and 30 characters")
    private String provider;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Cost must be greater than 0")
    @DecimalMax(value = "10000.0", inclusive = true, message = "Cost must be less than or equal to 10000")
    private double cost;

    @NotNull(message = "Insurance category is required")
    private InsuranceCategoryType category;
}
