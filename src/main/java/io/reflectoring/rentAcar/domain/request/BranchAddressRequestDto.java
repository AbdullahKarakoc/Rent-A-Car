package io.reflectoring.rentAcar.domain.request;

import lombok.Data;

@Data
public class BranchAddressRequestDto {

    private String street;
    private String city;
    private String country;
    private String zipCode;
}
