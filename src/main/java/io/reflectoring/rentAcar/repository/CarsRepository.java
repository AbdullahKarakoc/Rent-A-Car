package io.reflectoring.rentAcar.repository;

import io.reflectoring.rentAcar.domain.model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarsRepository extends JpaRepository<Cars, UUID> {}
