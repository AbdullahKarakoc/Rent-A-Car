package io.reflectoring.rentAcar.domain.request;

import io.reflectoring.rentAcar.enums.CountryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BranchAddressRequestDto {

    @NotBlank(message = "Street is required")
    @Size(min = 1, max = 25, message = "Street name must be between 1 and 25 characters")
    private String street;

    @NotBlank(message = "CityType is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "CityType name must be only characters")
    @Size(min = 1, max = 25, message = "City name must be between 1 and 25 characters")
    private String city;

    @NotBlank(message = "Zip code is required")
    @Pattern(regexp = "^[0-9\\s]+$", message = "Zip code must be numeric")
    @Size(min = 5, max = 10, message = "Zip code must be between 5 and 10 characters")
    private String zipCode;

    @NotNull(message = "CountryType is required")
    private CountryType country;

}
