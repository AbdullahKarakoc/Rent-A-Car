package io.reflectoring.rentAcar.service;

import io.reflectoring.rentAcar.domain.model.Cars;
import io.reflectoring.rentAcar.domain.model.Insurances;
import io.reflectoring.rentAcar.domain.model.Rentals;
import io.reflectoring.rentAcar.domain.request.InsurancesRequestDto;
import io.reflectoring.rentAcar.domain.response.InsurancesResponseDto;
import io.reflectoring.rentAcar.exception.DataNotFoundException;
import io.reflectoring.rentAcar.repository.CarsRepository;
import io.reflectoring.rentAcar.repository.InsurancesRepository;
import io.reflectoring.rentAcar.repository.RentalsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InsurancesService {

    private final InsurancesRepository insuranceRepository;
    private final ModelMapper modelMapper;

    public List<InsurancesResponseDto> getAllInsurances() {
        List<Insurances> insurances = insuranceRepository.findAll();
        return insurances.stream()
                .map(insurance -> modelMapper.map(insurance, InsurancesResponseDto.class))
                .collect(Collectors.toList());
    }

    public InsurancesResponseDto getInsuranceById(UUID id) {
        Insurances insurance = findById(id);
        return modelMapper.map(insurance, InsurancesResponseDto.class);
    }

    public InsurancesResponseDto saveInsurance(InsurancesRequestDto insuranceRequestDto) {
        Insurances insurance = modelMapper.map(insuranceRequestDto, Insurances.class);
        Insurances savedInsurance = save(insurance);
        return modelMapper.map(savedInsurance, InsurancesResponseDto.class);
    }

    public InsurancesResponseDto updateInsurance(UUID id, InsurancesRequestDto insuranceRequestDto) {
        Insurances existingInsurance = findById(id);
        modelMapper.map(insuranceRequestDto, existingInsurance);
        Insurances updatedInsurance = save(existingInsurance);
        return modelMapper.map(updatedInsurance, InsurancesResponseDto.class);
    }

    public void deleteInsurance(UUID id) {
        Insurances existingInsurance = findById(id);
        insuranceRepository.delete(existingInsurance);
    }

    public Insurances save(Insurances insurance) {
        return insuranceRepository.saveAndFlush(insurance);
    }

    public Insurances findById(UUID id) {
        return insuranceRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Insurance not found"));
    }
}
