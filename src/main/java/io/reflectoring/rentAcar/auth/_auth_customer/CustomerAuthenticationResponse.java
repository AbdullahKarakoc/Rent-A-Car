package io.reflectoring.rentAcar.auth._auth_customer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerAuthenticationResponse {
    private String token;
}
