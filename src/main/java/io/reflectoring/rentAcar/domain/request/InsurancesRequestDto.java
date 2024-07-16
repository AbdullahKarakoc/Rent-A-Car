package io.reflectoring.rentAcar.domain.request;

import io.reflectoring.rentAcar.enums.InsuranceCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class InsurancesRequestDto {


    @NotBlank(message = "Provider is required")
    private String provider;

    @NotNull(message = "Amount is required")
    private double cost;


    @NotBlank(message = "Insurance category is required")
    private InsuranceCategory category;
}
