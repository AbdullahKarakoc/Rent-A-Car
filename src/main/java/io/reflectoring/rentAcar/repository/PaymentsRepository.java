package io.reflectoring.rentAcar.repository;

import io.reflectoring.rentAcar.domain.model.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentsRepository extends JpaRepository<Payments, UUID> {}

