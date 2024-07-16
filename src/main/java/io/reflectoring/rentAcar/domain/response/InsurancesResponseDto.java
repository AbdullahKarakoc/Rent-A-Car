package io.reflectoring.rentAcar.domain.response;

import io.reflectoring.rentAcar.enums.InsuranceCategoryType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class InsurancesResponseDto {
    private UUID insuranceUUID;
    private String provider;
    private double cost;
    private InsuranceCategoryType category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}
