package io.reflectoring.rentAcar.controller;

import io.reflectoring.rentAcar.domain.request.RentalsRequestDto;
import io.reflectoring.rentAcar.domain.response.RentalsResponseDto;
import io.reflectoring.rentAcar.service.RentalsService;
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
@RequestMapping("/rentals")
@Tag(name = "Rentals-Controller", description = "Controller managing operations related to rentals")
@SecurityRequirement(name = "bearerAuth")
public class RentalsController {
    @Autowired
    private RentalsService rentalService;

    @Operation(
            summary = "Get all rentals",
            description = "An endpoint used to list all rentals.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @GetMapping
    public ResponseEntity<List<RentalsResponseDto>> getAllRentals() {
        List<RentalsResponseDto> allRentals = rentalService.getAllRentals();
        return ResponseEntity.ok(allRentals);
    }

    @Operation(
            summary = "Get rental by ID",
            description = "An endpoint used to get details of a rental by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved rental"),
                    @ApiResponse(responseCode = "404", description = "Rental not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<RentalsResponseDto> getRentalById(@PathVariable UUID id) {
        RentalsResponseDto rental = rentalService.getRentalById(id);
        return ResponseEntity.ok(rental);
    }

    @Operation(
            summary = "Save a new rental",
            description = "An endpoint used to save a new rental.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Rental successfully saved"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @PostMapping
    public ResponseEntity<RentalsResponseDto> saveRental(@Valid @RequestBody RentalsRequestDto rentalRequestDto) {
        RentalsResponseDto savedRental = rentalService.saveRental(rentalRequestDto);
        return ResponseEntity.ok(savedRental);
    }

    @Operation(
            summary = "Update rental",
            description = "An endpoint used to update an existing rental by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Rental successfully updated"),
                    @ApiResponse(responseCode = "404", description = "Rental not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<RentalsResponseDto> updateRental(@Valid @PathVariable UUID id, @RequestBody RentalsRequestDto rentalRequestDto) {
        RentalsResponseDto updatedRental = rentalService.updateRental(id, rentalRequestDto);
        return ResponseEntity.ok(updatedRental);
    }

    @Operation(
            summary = "Delete rental",
            description = "An endpoint used to delete an existing rental by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Rental successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "Rental not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable UUID id) {
        rentalService.deleteRental(id);
        return ResponseEntity.ok().build();
    }
}
