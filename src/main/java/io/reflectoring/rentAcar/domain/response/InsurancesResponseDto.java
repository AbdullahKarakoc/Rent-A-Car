package io.reflectoring.rentAcar.domain.response;

import io.reflectoring.rentAcar.enums.InsuranceCategory;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class InsurancesResponseDto {
    private UUID insuranceUUID;
    private String provider;
    private double amount;
    private InsuranceCategory category;
    private LocalDateTime createDate;
    private LocalDateTime lastModified;
    private String createBy;
    private String lastModifiedBy;
}
