package com.rapido.ride_service.repository;

import com.rapido.ride_service.entity.DriverLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverLocationRepository
        extends JpaRepository<DriverLocationEntity, Long> {

    List<DriverLocationEntity> findByRideId(Long rideId);

    List<DriverLocationEntity> findByDriverId(Long driverId);
}