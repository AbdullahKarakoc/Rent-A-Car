package io.reflectoring.rentAcar.controller;

import io.reflectoring.rentAcar.domain.request.CarsRequestDto;
import io.reflectoring.rentAcar.domain.response.CarsResponseDto;
import io.reflectoring.rentAcar.service.CarsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cars")
public class CarsController {
    @Autowired
    private CarsService carService;

    @GetMapping
    public List<CarsResponseDto> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public CarsResponseDto getCarById(@PathVariable UUID id) {
        return carService.getCarById(id);
    }

    @PostMapping
    public CarsResponseDto saveCar(@Valid @RequestBody CarsRequestDto carRequestDto) {
        return carService.saveCar(carRequestDto);
    }

    @PutMapping("/{id}")
    public CarsResponseDto updateCar(@Valid @PathVariable UUID id, @RequestBody CarsRequestDto carRequestDto) {
        return carService.updateCar(id, carRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable UUID id) {
        carService.deleteCar(id);
    }
}
