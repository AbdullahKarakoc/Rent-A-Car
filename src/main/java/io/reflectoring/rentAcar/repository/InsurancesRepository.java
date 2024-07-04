package io.reflectoring.rentAcar.repository;

import io.reflectoring.rentAcar.domain.model.Insurances;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InsurancesRepository extends JpaRepository<Insurances, UUID> {}

