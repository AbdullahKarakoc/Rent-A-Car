package io.reflectoring.rentAcar.service;

import io.reflectoring.rentAcar.domain.model.Branchs;
import io.reflectoring.rentAcar.domain.model.Cars;
import io.reflectoring.rentAcar.domain.model.Insurances;
import io.reflectoring.rentAcar.domain.request.CarsRequestDto;
import io.reflectoring.rentAcar.domain.response.BranchsResponseDto;
import io.reflectoring.rentAcar.domain.response.CarsResponseDto;
import io.reflectoring.rentAcar.domain.response.InsurancesResponseDto;
import io.reflectoring.rentAcar.exception.DataNotFoundException;
import io.reflectoring.rentAcar.repository.BranchsRepository;
import io.reflectoring.rentAcar.repository.CarsRepository;
import io.reflectoring.rentAcar.repository.InsurancesRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarsService {

    private final CarsRepository carRepository;
    private final BranchsService branchService;
    private final InsurancesService insuranceService;
    private final ModelMapper modelMapper;

    public List<CarsResponseDto> getAllCars() {
        List<Cars> cars = carRepository.findAll();
        return cars.stream()
                .map(car -> modelMapper.map(car, CarsResponseDto.class))
                .collect(Collectors.toList());
    }

    public CarsResponseDto getCarById(UUID id) {
        Cars car = findById(id);
        return modelMapper.map(car, CarsResponseDto.class);
    }

    public CarsResponseDto saveCar(CarsRequestDto carRequestDto) {
        Branchs branch = branchService.findById(carRequestDto.getBranchUUID());
        Insurances insurance = modelMapper.map(carRequestDto.getInsurances(), Insurances.class);
        Insurances savedInsurance = insuranceService.save(insurance); // Save insurance and get the model

        Cars car = modelMapper.map(carRequestDto, Cars.class);
        car.setBranch(branch);
        car.setInsurance(savedInsurance);
        Cars savedCar = save(car);

        return modelMapper.map(savedCar, CarsResponseDto.class);
    }

    public CarsResponseDto updateCar(UUID id, CarsRequestDto carRequestDto) {
        Cars existingCar = findById(id);

        Branchs branch = branchService.findById(carRequestDto.getBranchUUID());
        Insurances insurance = modelMapper.map(carRequestDto.getInsurances(), Insurances.class);
        Insurances savedInsurance = insuranceService.save(insurance); // Save insurance and get the model

        modelMapper.map(carRequestDto, existingCar);
        existingCar.setBranch(branch);
        existingCar.setInsurance(savedInsurance);

        Cars updatedCar = save(existingCar);
        return modelMapper.map(updatedCar, CarsResponseDto.class);
    }

    public void deleteCar(UUID id) {
        Cars car = findById(id);
        car.setDeleted(true);
        save(car);
    }

    public void setCarUnavailable(Cars car) {
        car.setAvailable(false);
        save(car);
    }

    public void setCarAvailable(Cars car) {
        car.setAvailable(true);
        save(car);
    }

    public Cars save(Cars car) {
        return carRepository.saveAndFlush(car);
    }

    public Cars findById(UUID id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Car not found"));
    }
}