package io.reflectoring.rentAcar.repository;

import io.reflectoring.rentAcar.domain.model.Rentals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RentalsRepository extends JpaRepository<Rentals, UUID> {
    List<Rentals> findByUser_UserUUID(UUID userUUID);

}
