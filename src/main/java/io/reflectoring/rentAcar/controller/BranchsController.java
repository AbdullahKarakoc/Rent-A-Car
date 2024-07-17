package io.reflectoring.rentAcar.controller;

import io.reflectoring.rentAcar.domain.request.BranchsRequestDto;
import io.reflectoring.rentAcar.domain.response.BranchsResponseDto;
import io.reflectoring.rentAcar.service.BranchsService;
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
@RequestMapping("/branches")
@Tag(name = "Branches-Controller", description = "Controller managing operations related to branches")
@SecurityRequirement(name = "bearerAuth")
public class BranchsController {
    @Autowired
    private BranchsService branchService;

    @Operation(
            summary = "Get all branches",
            description = "An endpoint used to list all branches.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @GetMapping
    public ResponseEntity<List<BranchsResponseDto>> getAllBranches() {
        List<BranchsResponseDto> allBranches = branchService.getAllBranches();
        return ResponseEntity.ok(allBranches);
    }

    @Operation(
            summary = "Get branch by ID",
            description = "An endpoint used to get details of a branch by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved branch"),
                    @ApiResponse(responseCode = "404", description = "Branch not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<BranchsResponseDto> getBranchById(@PathVariable UUID id) {
        BranchsResponseDto branch = branchService.getBranchById(id);
        return ResponseEntity.ok(branch);
    }

    @Operation(
            summary = "Save a new branch",
            description = "An endpoint used to save a new branch.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Branch successfully saved"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @PostMapping
    public ResponseEntity<BranchsResponseDto> saveBranch(@Valid @RequestBody BranchsRequestDto branchRequestDto) {
        BranchsResponseDto savedBranch = branchService.saveBranch(branchRequestDto);
        return ResponseEntity.ok(savedBranch);
    }

    @Operation(
            summary = "Update branch",
            description = "An endpoint used to update an existing branch by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Branch successfully updated"),
                    @ApiResponse(responseCode = "404", description = "Branch not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<BranchsResponseDto> updateBranch(@Valid @PathVariable UUID id, @RequestBody BranchsRequestDto branchRequestDto) {
        BranchsResponseDto updatedBranch = branchService.updateBranch(id, branchRequestDto);
        return ResponseEntity.ok(updatedBranch);
    }

    @Operation(
            summary = "Delete branch",
            description = "An endpoint used to delete an existing branch by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Branch successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "Branch not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable UUID id) {
        branchService.deleteBranch(id);
        return ResponseEntity.ok().build();
    }
}
