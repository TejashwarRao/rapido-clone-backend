package com.rapido.ride_service.repository;

import com.rapido.ride_service.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepository
        extends JpaRepository<Driver, Long> {

    List<Driver> findByAvailableTrueAndOnlineTrue();
}