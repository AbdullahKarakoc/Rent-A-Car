package io.reflectoring.rentAcar.controller;

import io.reflectoring.rentAcar.domain.request.InsurancesRequestDto;
import io.reflectoring.rentAcar.domain.response.InsurancesResponseDto;
import io.reflectoring.rentAcar.service.InsurancesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/insurances")
public class InsurancesController {
    @Autowired
    private InsurancesService insuranceService;

    @GetMapping
    public List<InsurancesResponseDto> getAllInsurances() {
        return insuranceService.getAllInsurances();
    }

    @GetMapping("/{id}")
    public InsurancesResponseDto getInsuranceById(@PathVariable UUID id) {
        return insuranceService.getInsuranceById(id);
    }

    @PostMapping
    public InsurancesResponseDto saveInsurance(@Valid @RequestBody InsurancesRequestDto insuranceRequestDto) {
        return insuranceService.saveInsurance(insuranceRequestDto);
    }

    @PutMapping("/{id}")
    public InsurancesResponseDto updateInsurance(@Valid @PathVariable UUID id, @RequestBody InsurancesRequestDto insuranceRequestDto) {
        return insuranceService.updateInsurance(id, insuranceRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteInsurance(@PathVariable UUID id) {
        insuranceService.deleteInsurance(id);
    }
}
