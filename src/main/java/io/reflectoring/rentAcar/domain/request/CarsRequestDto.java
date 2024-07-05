package io.reflectoring.rentAcar.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class CarsRequestDto {

    @NotBlank(message = "Model is required")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Model name must be alphanumeric")
    @Size(min = 1, max = 50, message = "Model name length must be between 1 and 50 characters")
    private String model;

    @NotBlank(message = "Brand is required")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Brand name must be alphanumeric")
    @Size(min = 1, max = 50, message = "Brand name length must be between 1 and 50 characters")
    private String brand;

    @NotNull(message = "Year is required")
    private Integer year;

    @NotBlank(message = "Color is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Color must be only characters")
    private String color;

    @NotBlank(message = "Registration number is required")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Registration number must be alphanumeric")
    private String registrationNumber;

    @NotNull(message = "Location ID is required")
    private UUID locationUUID;
}
