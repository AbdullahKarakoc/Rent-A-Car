package io.reflectoring.rentAcar.repository;

import io.reflectoring.rentAcar.domain.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomersRepository extends JpaRepository<Customers, UUID> {}

