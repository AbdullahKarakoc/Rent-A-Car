package io.reflectoring.rentAcar.domain.request;

import io.reflectoring.rentAcar.enums.InsuranceCategory;
import lombok.Data;

import java.util.UUID;

@Data
public class InsurancesRequestDto {
    private String provider;
    private double amount;
    private InsuranceCategory category;
}
