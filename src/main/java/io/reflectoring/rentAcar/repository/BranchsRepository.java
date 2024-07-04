package io.reflectoring.rentAcar.repository;

import io.reflectoring.rentAcar.domain.model.Branchs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BranchsRepository extends JpaRepository<Branchs, UUID> {}

