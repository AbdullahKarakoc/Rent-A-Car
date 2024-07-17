package io.reflectoring.rentAcar.controller;

import io.reflectoring.rentAcar.domain.request.BranchAddressRequestDto;
import io.reflectoring.rentAcar.domain.response.BranchAddressResponseDto;
import io.reflectoring.rentAcar.service.BranchAddressService;
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
@RequestMapping("/branch-addresses")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Branch Address-Controller", description = "Controller managing operations related to branch addresses")
public class BranchAddressController {
    @Autowired
    private BranchAddressService branchAddressService;

    @Operation(
            summary = "Get all branch addresses",
            description = "An endpoint used to list all branch addresses.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @GetMapping
    public ResponseEntity<List<BranchAddressResponseDto>> getAllBranchAddresses() {
        List<BranchAddressResponseDto> allBranchAddresses = branchAddressService.getAllBranchAddresses();
        return ResponseEntity.ok(allBranchAddresses);
    }

    @Operation(
            summary = "Get branch address by ID",
            description = "An endpoint used to get details of a branch address by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved branch address"),
                    @ApiResponse(responseCode = "404", description = "Branch address not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<BranchAddressResponseDto> getBranchAddressById(@PathVariable UUID id) {
        BranchAddressResponseDto branchAddress = branchAddressService.getBranchAddressById(id);
        return ResponseEntity.ok(branchAddress);
    }

    @Operation(
            summary = "Save a new branch address",
            description = "An endpoint used to save a new branch address.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Branch address successfully saved"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @PostMapping
    public ResponseEntity<BranchAddressResponseDto> saveBranchAddress(@Valid @RequestBody BranchAddressRequestDto branchAddressRequestDto) {
        BranchAddressResponseDto savedBranchAddress = branchAddressService.saveBranchAddress(branchAddressRequestDto);
        return ResponseEntity.ok(savedBranchAddress);
    }

    @Operation(
            summary = "Update branch address",
            description = "An endpoint used to update an existing branch address by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Branch address successfully updated"),
                    @ApiResponse(responseCode = "404", description = "Branch address not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<BranchAddressResponseDto> updateBranchAddress(@Valid @PathVariable UUID id, @RequestBody BranchAddressRequestDto branchAddressRequestDto) {
        BranchAddressResponseDto updatedBranchAddress = branchAddressService.updateBranchAddress(id, branchAddressRequestDto);
        return ResponseEntity.ok(updatedBranchAddress);
    }

    @Operation(
            summary = "Delete branch address",
            description = "An endpoint used to delete an existing branch address by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Branch address successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "Branch address not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranchAddress(@PathVariable UUID id) {
        branchAddressService.deleteBranchAddress(id);
        return ResponseEntity.ok().build();
    }
}

