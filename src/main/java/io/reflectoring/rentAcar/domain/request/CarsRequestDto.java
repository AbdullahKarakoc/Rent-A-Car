package io.reflectoring.rentAcar.domain.request;

import io.reflectoring.rentAcar.enums.BrandType;
import io.reflectoring.rentAcar.enums.ColorType;
import io.reflectoring.rentAcar.enums.SegmentType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.UUID;

@Data
public class CarsRequestDto {

    @NotNull(message = "Brand is required")
    private BrandType brand;

    @NotNull(message = "Segment is required")
    private SegmentType segment;

    @NotBlank(message = "Model is required")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Model name must be alphanumeric")
    @Size(min = 1, max = 50, message = "Model name length must be between 1 and 50 characters")
    private String model;

    @NotNull(message = "Color is required")
    private ColorType color;

    @NotNull(message = "Car's year is required")
    @Min(value = 1886, message = "Year must be later than 1886")  // The year the first car was made
    private Integer year;

    @NotBlank(message = "Plate number is required")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Plate number must be alphanumeric")
    @Size(min = 5, max = 7, message = "Plate number length must be between 1 and 50 characters")
    private String plateNumber;

    @NotNull(message = "Car's hourly price is required")
    @Min(value = 1, message = "Hourly price must be at least 1")
    @Max(value = 1000, message = "Hourly price must be less than or equal to 1000")
    private int pricePerHour;


    @Valid
    @NotNull(message = "Branch address is required")
    private InsurancesRequestDto insurances;

    @Valid
    @NotNull(message = "Branch ID is required")
    private UUID branchUUID;
}
