package io.reflectoring.rentAcar.domain.request;

import lombok.Data;

@Data
public class CustomersRequestDto {
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String email;
}
