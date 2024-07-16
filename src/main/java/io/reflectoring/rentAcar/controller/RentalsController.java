package io.reflectoring.rentAcar.controller;

import io.reflectoring.rentAcar.domain.request.RentalsRequestDto;
import io.reflectoring.rentAcar.domain.response.RentalsResponseDto;
import io.reflectoring.rentAcar.service.RentalsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rentals")
public class RentalsController {
    @Autowired
    private RentalsService rentalService;

    @GetMapping
    public List<RentalsResponseDto> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    public RentalsResponseDto getRentalById(@PathVariable UUID id) {
        return rentalService.getRentalById(id);
    }

    @PostMapping
    public RentalsResponseDto saveRental(@Valid @RequestBody RentalsRequestDto rentalRequestDto) {
        return rentalService.saveRental(rentalRequestDto);
    }

    @PutMapping("/{id}")
    public RentalsResponseDto updateRental(@Valid @PathVariable UUID id, @RequestBody RentalsRequestDto rentalRequestDto) {
        return rentalService.updateRental(id, rentalRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable UUID id) {
        rentalService.deleteRental(id);
    }
}
