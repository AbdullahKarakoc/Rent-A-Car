package io.reflectoring.rentAcar.service;

import io.reflectoring.rentAcar.domain.model.Cars;
import io.reflectoring.rentAcar.domain.request.CarsRequestDto;
import io.reflectoring.rentAcar.domain.response.CarsResponseDto;
import io.reflectoring.rentAcar.repository.CarsRepository;
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
    private ModelMapper modelMapper;

    public List<CarsResponseDto> getAllCars() {
        List<Cars> cars = carRepository.findAll();
        return cars.stream()
                .map(car -> modelMapper.map(car, CarsResponseDto.class))
                .collect(Collectors.toList());
    }

    public CarsResponseDto getCarById(UUID id) {
        Cars car = carRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Car not found"));
        return modelMapper.map(car, CarsResponseDto.class);
    }

    public CarsResponseDto saveCar(CarsRequestDto carRequestDto) {
        Cars car = modelMapper.map(carRequestDto, Cars.class);
        car = carRepository.save(car);
        return modelMapper.map(car, CarsResponseDto.class);
    }

    public CarsResponseDto updateCar(UUID id, CarsRequestDto carRequestDto) {
        Cars existingCar = carRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Car not found"));
        modelMapper.map(carRequestDto, existingCar);
        Cars updatedCar = carRepository.save(existingCar);
        return modelMapper.map(updatedCar, CarsResponseDto.class);
    }

    public void deleteCar(UUID id) {
        Cars car = carRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Car not found"));
        carRepository.delete(car);
    }
}
}
