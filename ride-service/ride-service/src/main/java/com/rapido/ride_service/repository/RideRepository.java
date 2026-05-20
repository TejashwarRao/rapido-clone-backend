package com.rapido.ride_service.repository;

import com.rapido.ride_service.entity.Ride;

import com.rapido.ride_service.entity.RideStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepository
        extends JpaRepository<Ride, Long> {

    // FIND BY USER EMAIL
    List<Ride> findByUserEmail(
            String userEmail
    );

    // FIND BY DRIVER EMAIL
    List<Ride> findByDriverEmail(
            String driverEmail
    );

    // FIND BY STATUS
    List<Ride> findByStatus(
            RideStatus status
    );
}