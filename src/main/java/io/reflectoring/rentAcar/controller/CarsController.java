package io.reflectoring.rentAcar.controller;

import io.reflectoring.rentAcar.domain.request.CarsRequestDto;
import io.reflectoring.rentAcar.domain.response.CarsResponseDto;
import io.reflectoring.rentAcar.service.CarsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cars")
@Tag(name = "Cars-Controller", description = "Controller managing operations related to cars")
@SecurityRequirement(name = "bearerAuth")
public class CarsController {
    @Autowired
    private CarsService carService;

    @Operation(
            summary = "Get all cars",
            description = "An endpoint used to list all cars.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @GetMapping
    public ResponseEntity<List<CarsResponseDto>> getAllCars() {
        List<CarsResponseDto> allCars = carService.getAllCars();
        return ResponseEntity.ok(allCars);
    }

    @Operation(
            summary = "Get car by ID",
            description = "An endpoint used to get details of a car by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved car"),
                    @ApiResponse(responseCode = "404", description = "Car not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<CarsResponseDto> getCarById(@PathVariable UUID id) {
        CarsResponseDto car = carService.getCarById(id);
        return ResponseEntity.ok(car);
    }

    @Operation(
            summary = "Save a new car",
            description = "An endpoint used to save a new car.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Car successfully saved"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @PostMapping
    public ResponseEntity<CarsResponseDto> saveCar(@Valid @RequestBody CarsRequestDto carRequestDto) {
        CarsResponseDto savedCar = carService.saveCar(carRequestDto);
        return ResponseEntity.ok(savedCar);
    }

    @Operation(
            summary = "Update car",
            description = "An endpoint used to update an existing car by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Car successfully updated"),
                    @ApiResponse(responseCode = "404", description = "Car not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<CarsResponseDto> updateCar(@Valid @PathVariable UUID id, @RequestBody CarsRequestDto carRequestDto) {
        CarsResponseDto updatedCar = carService.updateCar(id, carRequestDto);
        return ResponseEntity.ok(updatedCar);
    }

    @Operation(
            summary = "Delete car",
            description = "An endpoint used to delete an existing car by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Car successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "Car not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable UUID id) {
        carService.deleteCar(id);
        return ResponseEntity.ok().build();
    }
}
