package io.reflectoring.rentAcar.domain.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CustomersResponseDto {
    private UUID customerUUID;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String email;


}
