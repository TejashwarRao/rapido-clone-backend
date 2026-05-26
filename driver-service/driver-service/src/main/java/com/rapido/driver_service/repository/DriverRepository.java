package com.rapido.driver_service.repository;

import com.rapido.driver_service.entity.Driver;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository
        extends JpaRepository<Driver, Long> {
}