package io.reflectoring.rentAcar.domain.request;

import io.reflectoring.rentAcar.enums.CountryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BranchAddressRequestDto {

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "CityType is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "CityType name must be only characters")
    private String city;

    @NotBlank(message = "Zip code is required")
    @Pattern(regexp = "^[0-9\\s]+$", message = "Zip code must be numeric")
    private String zipCode;

    @NotNull(message = "CountryType is required")
    private CountryType country;

}
