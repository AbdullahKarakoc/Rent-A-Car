package io.reflectoring.rentAcar.domain.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class StaffsResponseDto {
    private UUID staffUUID;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private BranchsResponseDto branch;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}
