package io.reflectoring.rentAcar.controller;

import io.reflectoring.rentAcar.domain.request.InsurancesRequestDto;
import io.reflectoring.rentAcar.domain.response.InsurancesResponseDto;
import io.reflectoring.rentAcar.service.InsurancesService;
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
@RequestMapping("/insurances")
@Tag(name = "Insurances-Controller", description = "Controller managing operations related to insurances")
@SecurityRequirement(name = "bearerAuth")
public class InsurancesController {
    @Autowired
    private InsurancesService insuranceService;

    @Operation(
            summary = "Get all insurances",
            description = "An endpoint used to list all insurances.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @GetMapping
    public ResponseEntity<List<InsurancesResponseDto>> getAllInsurances() {
        List<InsurancesResponseDto> allInsurances = insuranceService.getAllInsurances();
        return ResponseEntity.ok(allInsurances);
    }

    @Operation(
            summary = "Get insurance by ID",
            description = "An endpoint used to get details of an insurance by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved insurance"),
                    @ApiResponse(responseCode = "404", description = "Insurance not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<InsurancesResponseDto> getInsuranceById(@PathVariable UUID id) {
        InsurancesResponseDto insurance = insuranceService.getInsuranceById(id);
        return ResponseEntity.ok(insurance);
    }

    @Operation(
            summary = "Save a new insurance",
            description = "An endpoint used to save a new insurance.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Insurance successfully saved"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @PostMapping
    public ResponseEntity<InsurancesResponseDto> saveInsurance(@Valid @RequestBody InsurancesRequestDto insuranceRequestDto) {
        InsurancesResponseDto savedInsurance = insuranceService.saveInsurance(insuranceRequestDto);
        return ResponseEntity.ok(savedInsurance);
    }

    @Operation(
            summary = "Update insurance",
            description = "An endpoint used to update an existing insurance by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Insurance successfully updated"),
                    @ApiResponse(responseCode = "404", description = "Insurance not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<InsurancesResponseDto> updateInsurance(@Valid @PathVariable UUID id, @RequestBody InsurancesRequestDto insuranceRequestDto) {
        InsurancesResponseDto updatedInsurance = insuranceService.updateInsurance(id, insuranceRequestDto);
        return ResponseEntity.ok(updatedInsurance);
    }

    @Operation(
            summary = "Delete insurance",
            description = "An endpoint used to delete an existing insurance by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Insurance successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "Insurance not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsurance(@PathVariable UUID id) {
        insuranceService.deleteInsurance(id);
        return ResponseEntity.ok().build();
    }
}
