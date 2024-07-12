package io.reflectoring.rentAcar.domain.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CarsResponseDto {
    private UUID carUUID;
    private String model;
    private String brand;
    private int year;
    private String color;
    private String registrationNumber;
    private boolean available;

}
