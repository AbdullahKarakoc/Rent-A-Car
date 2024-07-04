package io.reflectoring.rentAcar.domain.request;

import lombok.Data;

@Data
public class CarsRequestDto {
    private String model;
    private String brand;
    private int year;
    private String color;
    private String registrationNumber;
    private boolean available;
}
