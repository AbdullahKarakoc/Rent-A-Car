package io.reflectoring.rentAcar.repository;

import io.reflectoring.rentAcar.domain.model.BranchAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BranchAddressRepository extends JpaRepository<BranchAddress, UUID> {}

