package io.reflectoring.rentAcar.domain.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BranchsResponseDto {
    private UUID branchUUID;
    private String branchName;
    private LocalDateTime createDate;
    private LocalDateTime lastModified;
    private String createBy;
    private String lastModifiedBy;
}
