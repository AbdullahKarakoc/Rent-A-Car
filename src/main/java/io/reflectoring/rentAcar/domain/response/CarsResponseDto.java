package io.reflectoring.rentAcar.domain.response;

import io.reflectoring.rentAcar.enums.BrandType;
import io.reflectoring.rentAcar.enums.ColorType;
import io.reflectoring.rentAcar.enums.SegmentType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CarsResponseDto {
    private UUID carUUID;
    private BrandType brand;
    private SegmentType segment;
    private String model;
    private ColorType color;
    private Integer year;
    private String plateNumber;
    private int pricePerHour;
    private boolean isAvailable;
    private InsurancesResponseDto insurance;
    private BranchsResponseDto branch;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}
