package io.reflectoring.rentAcar.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomersRequestDto {

    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "First name must be only characters")
    @Size(min = 1, max = 50, message = "First name length must be between 1 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Last name must be only characters")
    @Size(min = 1, max = 50, message = "Last name length must be between 1 and 50 characters")
    private String lastName;

    @NotBlank(message = "License number is required")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "License number must be alphanumeric")
    @Size(min = 1, max = 50, message = "License number length must be between 1 and 50 characters")
    private String licenseNumber;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9\\s]+$", message = "Phone number must be numeric")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
}
