package io.reflectoring.rentAcar.domain.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BranchAddressResponseDto {
    private UUID addressUUID;
    private String street;
    private String city;
    private String country;
    private String zipCode;
    private LocalDateTime createDate;
    private LocalDateTime lastModified;
    private String createBy;
    private String lastModifiedBy;
}
