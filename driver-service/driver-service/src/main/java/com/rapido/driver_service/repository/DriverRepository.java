package com.rapido.driver_service.repository;

import com.rapido.driver_service.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByEmail(String email);

    List<Driver> findByAvailableTrueAndOnlineTrue();

}