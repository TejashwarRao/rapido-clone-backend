package com.rapido.driver_service.repository;

import com.rapido.driver_service.entity.Driver;

import jakarta.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Lock;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DriverRepository
        extends JpaRepository<Driver, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)

    @Query("""
            SELECT d
            FROM Driver d
            WHERE d.id = :driverId
            """)
    Optional<Driver> findDriverForUpdate(
            @Param("driverId") Long driverId
    );

    Optional<Driver> findByAvailableTrue();
}