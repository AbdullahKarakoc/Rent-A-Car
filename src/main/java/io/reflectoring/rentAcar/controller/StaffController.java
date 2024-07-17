package io.reflectoring.rentAcar.controller;

import io.reflectoring.rentAcar.domain.request.StaffsRequestDto;
import io.reflectoring.rentAcar.domain.response.StaffsResponseDto;
import io.reflectoring.rentAcar.service.StaffService;
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
@RequestMapping("/staff")
@Tag(name = "Staff-Controller", description = "Controller managing operations related to staff members")
@SecurityRequirement(name = "bearerAuth")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @Operation(
            summary = "Get all staff members",
            description = "An endpoint used to list all staff members.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @GetMapping
    public ResponseEntity<List<StaffsResponseDto>> getAllStaffs() {
        List<StaffsResponseDto> allStaffs = staffService.getAllStaffs();
        return ResponseEntity.ok(allStaffs);
    }

    @Operation(
            summary = "Get staff member by ID",
            description = "An endpoint used to get details of a staff member by their ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved staff member"),
                    @ApiResponse(responseCode = "404", description = "Staff member not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<StaffsResponseDto> getStaffById(@PathVariable UUID id) {
        StaffsResponseDto staff = staffService.getStaffById(id);
        return ResponseEntity.ok(staff);
    }

    @Operation(
            summary = "Save a new staff member",
            description = "An endpoint used to save a new staff member.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Staff member successfully saved"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @PostMapping
    public ResponseEntity<StaffsResponseDto> saveStaff(@Valid @RequestBody StaffsRequestDto staffRequestDto) {
        StaffsResponseDto savedStaff = staffService.saveStaff(staffRequestDto);
        return ResponseEntity.ok(savedStaff);
    }

    @Operation(
            summary = "Update staff member",
            description = "An endpoint used to update an existing staff member by their ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Staff member successfully updated"),
                    @ApiResponse(responseCode = "404", description = "Staff member not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<StaffsResponseDto> updateStaff(@Valid @PathVariable UUID id, @RequestBody StaffsRequestDto staffRequestDto) {
        StaffsResponseDto updatedStaff = staffService.updateStaff(id, staffRequestDto);
        return ResponseEntity.ok(updatedStaff);
    }

    @Operation(
            summary = "Delete staff member",
            description = "An endpoint used to delete an existing staff member by their ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Staff member successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "Staff member not found"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable UUID id) {
        staffService.deleteStaff(id);
        return ResponseEntity.ok().build();
    }
}
