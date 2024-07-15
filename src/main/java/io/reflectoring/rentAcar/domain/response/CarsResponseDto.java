package io.reflectoring.rentAcar.domain.response;

import io.reflectoring.rentAcar.enums.Brand;
import io.reflectoring.rentAcar.enums.Color;
import io.reflectoring.rentAcar.enums.Segment;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CarsResponseDto {
    private UUID carUUID;
    private Brand brand;
    private Segment segment;
    private String model;
    private Color color;
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
