package io.reflectoring.rentAcar.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class BranchsRequestDto {

    @NotBlank(message = "Branch name is required")
    private String branchName;

    @NotNull(message = "Branch address ID is required")
    private UUID branchAddressUUID;
}
