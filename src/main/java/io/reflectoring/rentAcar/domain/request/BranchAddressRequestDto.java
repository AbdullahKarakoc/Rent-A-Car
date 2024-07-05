package io.reflectoring.rentAcar.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BranchAddressRequestDto {

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "City name must be only characters")
    private String city;

    @NotBlank(message = "Country is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Country name must be only characters")
    private String country;

    @NotBlank(message = "Zip code is required")
    @Pattern(regexp = "^[0-9\\s]+$", message = "Zip code must be numeric")
    private String zipCode;
}
