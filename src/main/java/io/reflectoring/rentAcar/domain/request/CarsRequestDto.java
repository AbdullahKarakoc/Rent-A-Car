package io.reflectoring.rentAcar.domain.request;

import io.reflectoring.rentAcar.enums.Brand;
import io.reflectoring.rentAcar.enums.Color;
import io.reflectoring.rentAcar.enums.Segment;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Data
public class CarsRequestDto {

    @NotBlank(message = "Brand is required")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Brand name must be alphanumeric")
    @Size(min = 1, max = 50, message = "Brand name length must be between 1 and 50 characters")
    private Brand brand;

    @NotBlank(message = "Segment is required")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Segment name must be alphanumeric")
    @Size(min = 1, max = 50, message = "Segment name length must be between 1 and 50 characters")
    private Segment segment;

    @NotBlank(message = "Model is required")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Model name must be alphanumeric")
    @Size(min = 1, max = 50, message = "Model name length must be between 1 and 50 characters")
    private String model;

    @NotBlank(message = "Color is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Color must be only characters")
    private Color color;

    @NotNull(message = "Car's year is required")
    @Min(value = 1886, message = "Year must be later than 1886")  // The year the first car was made
    private Integer year;

    @NotBlank(message = "Plate number is required")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Plate number must be alphanumeric")
    @Size(min = 5, max = 7, message = "Plate number length must be between 1 and 50 characters")
    private String plateNumber;

    @NotNull(message = "Car's hourly price is required")
    private int pricePerHour;



    @NotNull(message = "Branch address is required")
    private InsurancesRequestDto insurances;

    @NotNull(message = "Branch ID is required")
    private UUID branchUUID;
}
