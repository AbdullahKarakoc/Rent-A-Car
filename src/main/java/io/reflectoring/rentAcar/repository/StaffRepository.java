package io.reflectoring.rentAcar.repository;

import io.reflectoring.rentAcar.domain.model.Staffs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StaffRepository extends JpaRepository<Staffs, UUID> {}
