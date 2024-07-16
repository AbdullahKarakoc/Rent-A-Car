package io.reflectoring.rentAcar.controller;

import io.reflectoring.rentAcar.domain.request.BranchAddressRequestDto;
import io.reflectoring.rentAcar.domain.response.BranchAddressResponseDto;
import io.reflectoring.rentAcar.service.BranchAddressService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/branch-addresses")
@SecurityRequirement(name = "bearerAuth")
public class BranchAddressController {
    @Autowired
    private BranchAddressService branchAddressService;

    @GetMapping
    public List<BranchAddressResponseDto> getAllBranchAddresses() {
        return branchAddressService.getAllBranchAddresses();
    }

    @GetMapping("/{id}")
    public BranchAddressResponseDto getBranchAddressById(@PathVariable UUID id) {
        return branchAddressService.getBranchAddressById(id);
    }

    @PostMapping
    public BranchAddressResponseDto saveBranchAddress(@Valid @RequestBody BranchAddressRequestDto branchAddressRequestDto) {
        return branchAddressService.saveBranchAddress(branchAddressRequestDto);
    }

    @PutMapping("/{id}")
    public BranchAddressResponseDto updateBranchAddress(@Valid @PathVariable UUID id, @RequestBody BranchAddressRequestDto branchAddressRequestDto) {
        return branchAddressService.updateBranchAddress(id, branchAddressRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBranchAddress(@PathVariable UUID id) {
        branchAddressService.deleteBranchAddress(id);
    }
}

