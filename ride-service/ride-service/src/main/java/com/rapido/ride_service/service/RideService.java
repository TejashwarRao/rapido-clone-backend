package com.rapido.ride_service.service;

import com.rapido.ride_service.entity.Ride;

import com.rapido.ride_service.entity.RideStatus;

import com.rapido.ride_service.repository.RideRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    private final RideRepository rideRepository;

    public RideService(
            RideRepository rideRepository
    ) {

        this.rideRepository =
                rideRepository;
    }

    // CREATE RIDE
    public Ride createRide(
            Ride ride
    ) {

        ride.setStatus(
                RideStatus.REQUESTED
        );

        ride.setFare(
                150.0
        );

        return rideRepository.save(
                ride
        );
    }

    // GET ALL RIDES
    public List<Ride> getAllRides() {

        return rideRepository.findAll();
    }

    // START RIDE
    public Ride startRide(
            Long rideId
    ) {

        Ride ride =
                rideRepository.findById(
                        rideId
                ).orElseThrow(
                        () -> new RuntimeException(
                                "Ride not found"
                        )
                );

        ride.setStatus(
                RideStatus.STARTED
        );

        return rideRepository.save(
                ride
        );
    }

    // COMPLETE RIDE
    public Ride completeRide(
            Long rideId
    ) {

        Ride ride =
                rideRepository.findById(
                        rideId
                ).orElseThrow(
                        () -> new RuntimeException(
                                "Ride not found"
                        )
                );

        ride.setStatus(
                RideStatus.COMPLETED
        );

        return rideRepository.save(
                ride
        );
    }

    // CANCEL RIDE
    public Ride cancelRide(
            Long rideId
    ) {

        Ride ride =
                rideRepository.findById(
                        rideId
                ).orElseThrow(
                        () -> new RuntimeException(
                                "Ride not found"
                        )
                );

        ride.setStatus(
                RideStatus.CANCELLED
        );

        return rideRepository.save(
                ride
        );
    }
}