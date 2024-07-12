package io.reflectoring.rentAcar.service;

import io.reflectoring.rentAcar.domain.model.Branchs;
import io.reflectoring.rentAcar.domain.model.Cars;
import io.reflectoring.rentAcar.domain.model.Insurances;
import io.reflectoring.rentAcar.domain.request.CarsRequestDto;
import io.reflectoring.rentAcar.domain.response.CarsResponseDto;
import io.reflectoring.rentAcar.exception.DataNotFoundException;
import io.reflectoring.rentAcar.repository.BranchsRepository;
import io.reflectoring.rentAcar.repository.CarsRepository;
import io.reflectoring.rentAcar.repository.InsurancesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarsService {

    @Autowired
    private CarsRepository carRepository;

    @Autowired
    private BranchsRepository branchRepository;

    @Autowired
    private InsurancesRepository insuranceRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CarsResponseDto> getAllCars() {
        List<Cars> cars = carRepository.findAll();
        return cars.stream()
                .map(car -> modelMapper.map(car, CarsResponseDto.class))
                .collect(Collectors.toList());
    }

    public CarsResponseDto getCarById(UUID id) {
        Cars car = carRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Car not found"));
        return modelMapper.map(car, CarsResponseDto.class);
    }

    public CarsResponseDto saveCar(CarsRequestDto carRequestDto) {
        Branchs branch = branchRepository.findById(carRequestDto.getBranchUUID())
                .orElseThrow(() -> new DataNotFoundException("Branch not found"));

        Insurances insurance = modelMapper.map(carRequestDto.getInsurances(), Insurances.class);
        insurance = insuranceRepository.save(insurance);

        Cars car = modelMapper.map(carRequestDto, Cars.class);
        car.setBranch(branch);
        car.setInsurance(insurance);
        car = carRepository.save(car);

        return modelMapper.map(car, CarsResponseDto.class);
    }

    public CarsResponseDto updateCar(UUID id, CarsRequestDto carRequestDto) {
        Cars existingCar = carRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Car not found"));

        Branchs branch = branchRepository.findById(carRequestDto.getBranchUUID())
                .orElseThrow(() -> new DataNotFoundException("Branch not found"));

        Insurances insurance = modelMapper.map(carRequestDto.getInsurances(), Insurances.class);
        insurance = insuranceRepository.save(insurance);

        modelMapper.map(carRequestDto, existingCar);
        existingCar.setBranch(branch);
        existingCar.setInsurance(insurance);

        Cars updatedCar = carRepository.save(existingCar);
        return modelMapper.map(updatedCar, CarsResponseDto.class);
    }

    public void deleteCar(UUID id) {
        Cars car = carRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Car not found"));
        car.setDeleted(true);
        carRepository.save(car);
    }
}