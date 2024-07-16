package io.reflectoring.rentAcar.domain.request;

import io.reflectoring.rentAcar.enums.InsuranceCategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InsurancesRequestDto {


    @NotBlank(message = "Provider is required")
    private String provider;

    @NotNull(message = "Amount is required")
    private double cost;


    @NotNull(message = "Insurance category is required")
    private InsuranceCategoryType category;
}
