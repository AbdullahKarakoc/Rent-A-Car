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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InsurancesService {
    @Autowired
    private InsurancesRepository insuranceRepository;

    @Autowired
    private CarsRepository carRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<InsurancesResponseDto> getAllInsurances() {
        List<Insurances> insurances = insuranceRepository.findAll();
        return insurances.stream()
                .map(insurance -> modelMapper.map(insurance, InsurancesResponseDto.class))
                .collect(Collectors.toList());
    }

    public InsurancesResponseDto getInsuranceById(UUID id) {
        Insurances insurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Insurance not found"));
        return modelMapper.map(insurance, InsurancesResponseDto.class);
    }

    public InsurancesResponseDto saveInsurance(InsurancesRequestDto insuranceRequestDto) {
        Cars car = carRepository.findById(insuranceRequestDto.getCarUUID())
                .orElseThrow(() -> new DataNotFoundException("Car not found"));

        Insurances insurance = modelMapper.map(insuranceRequestDto, Insurances.class);
        insurance.setCar(car);
        insurance = insuranceRepository.save(insurance);
        return modelMapper.map(insurance, InsurancesResponseDto.class);
    }

    public InsurancesResponseDto updateInsurance(UUID id, InsurancesRequestDto insuranceRequestDto) {
        Insurances existingInsurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Insurance not found"));

        Cars car = carRepository.findById(insuranceRequestDto.getCarUUID())
                .orElseThrow(() -> new DataNotFoundException("Car not found"));

        modelMapper.map(insuranceRequestDto, existingInsurance);
        existingInsurance.setCar(car);
        Insurances updatedInsurance = insuranceRepository.save(existingInsurance);
        return modelMapper.map(updatedInsurance, InsurancesResponseDto.class);
    }

    public void deleteInsurance(UUID id) {
        Insurances insurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Insurance not found"));
        insuranceRepository.delete(insurance);
    }
}