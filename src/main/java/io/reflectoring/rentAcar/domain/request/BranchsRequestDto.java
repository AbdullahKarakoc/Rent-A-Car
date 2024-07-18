package io.reflectoring.rentAcar.domain.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class BranchsRequestDto {

    @NotBlank(message = "Branch name is required")
    @Size(min = 1, max = 25, message = "Branch name must be between 1 and 25 characters")
    private String branchName;

    @Valid
    @NotNull(message = "Branch address is required")
    private BranchAddressRequestDto branchAddress;

}
