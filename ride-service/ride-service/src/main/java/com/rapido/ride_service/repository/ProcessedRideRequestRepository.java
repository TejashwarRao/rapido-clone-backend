package com.rapido.ride_service.repository;

import com.rapido.ride_service.entity.ProcessedRideRequest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedRideRequestRepository
        extends JpaRepository<
        ProcessedRideRequest,
        String
        > {
}