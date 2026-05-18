package com.rapido.ride_service.service;

import com.rapido.ride_service.algorithm.DriverMatchingAlgorithm;
import com.rapido.ride_service.dto.RideRequestDTO;
import com.rapido.ride_service.dto.RideResponseDTO;
import com.rapido.ride_service.entity.Driver;
import com.rapido.ride_service.entity.Ride;
import com.rapido.ride_service.entity.RideStatus;
import com.rapido.ride_service.repository.DriverRepository;
import com.rapido.ride_service.repository.RideRepository;
import com.rapido.ride_service.util.DistanceUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RideService {

    private final RideRepository rideRepository;

    private final DriverRepository driverRepository;

    private final DriverMatchingAlgorithm driverMatchingAlgorithm;

    private final RideEventService rideEventService;

    public RideService(
            RideRepository rideRepository,
            DriverRepository driverRepository,
            DriverMatchingAlgorithm driverMatchingAlgorithm,
            RideEventService rideEventService
    ) {

        this.rideRepository = rideRepository;

        this.driverRepository = driverRepository;

        this.driverMatchingAlgorithm =
                driverMatchingAlgorithm;

        this.rideEventService =
                rideEventService;
    }

    @Transactional
    public RideResponseDTO requestRide(
            Long userId,
            RideRequestDTO dto
    ) {

        List<RideStatus> activeStatuses =
                List.of(
                        RideStatus.REQUESTED,
                        RideStatus.ACCEPTED,
                        RideStatus.STARTED
                );

        rideRepository.findByUserIdAndStatusIn(
                userId,
                activeStatuses
        ).ifPresent(ride -> {

            throw new RuntimeException(
                    "User already has active ride"
            );
        });

        List<Driver> availableDrivers =
                driverRepository
                        .findByAvailableTrueAndOnlineTrue();

        if (availableDrivers.isEmpty()) {

            throw new RuntimeException(
                    "No drivers available"
            );
        }

        Driver nearestDriver =
                driverMatchingAlgorithm
                        .findNearestDriver(
                                availableDrivers,
                                dto.getPickupLatitude(),
                                dto.getPickupLongitude()
                        );

        nearestDriver.setAvailable(false);

        driverRepository.save(nearestDriver);

        double distance =
                DistanceUtil.calculateDistance(
                        dto.getPickupLatitude(),
                        dto.getPickupLongitude(),
                        dto.getDropLatitude(),
                        dto.getDropLongitude()
                );

        double fare =
                40 + (distance * 12);

        Ride ride = new Ride();

        ride.setUserId(userId);

        ride.setDriverId(nearestDriver.getId());

        ride.setPickupLatitude(
                dto.getPickupLatitude()
        );

        ride.setPickupLongitude(
                dto.getPickupLongitude()
        );

        ride.setDropLatitude(
                dto.getDropLatitude()
        );

        ride.setDropLongitude(
                dto.getDropLongitude()
        );

        ride.setStatus(RideStatus.REQUESTED);

        ride.setEstimatedDistance(distance);

        ride.setEstimatedFare(fare);

        ride.setRequestedAt(
                LocalDateTime.now()
        );

        Ride savedRide =
                rideRepository.save(ride);

        rideEventService.publishRideStatus(
                savedRide.getId(),
                RideStatus.REQUESTED,
                "Driver Assigned Successfully"
        );

        RideResponseDTO response =
                new RideResponseDTO();

        response.setRideId(savedRide.getId());

        response.setUserId(savedRide.getUserId());

        response.setDriverId(savedRide.getDriverId());

        response.setStatus(savedRide.getStatus());

        response.setEstimatedDistance(
                savedRide.getEstimatedDistance()
        );

        response.setEstimatedFare(
                savedRide.getEstimatedFare()
        );

        response.setMessage(
                "Ride requested successfully"
        );

        return response;
    }

    @Transactional
    public RideResponseDTO acceptRide(
            Long rideId
    ) {

        Ride ride =
                rideRepository.findById(rideId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Ride not found"
                                ));

        ride.setStatus(RideStatus.ACCEPTED);

        Ride updatedRide =
                rideRepository.save(ride);

        rideEventService.publishRideStatus(
                updatedRide.getId(),
                RideStatus.ACCEPTED,
                "Ride Accepted"
        );

        RideResponseDTO response =
                new RideResponseDTO();

        response.setRideId(updatedRide.getId());

        response.setStatus(updatedRide.getStatus());

        response.setMessage("Ride Accepted");

        return response;
    }

    @Transactional
    public RideResponseDTO startRide(
            Long rideId
    ) {

        Ride ride =
                rideRepository.findById(rideId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Ride not found"
                                ));

        ride.setStatus(RideStatus.STARTED);

        Ride updatedRide =
                rideRepository.save(ride);

        rideEventService.publishRideStatus(
                updatedRide.getId(),
                RideStatus.STARTED,
                "Ride Started"
        );

        RideResponseDTO response =
                new RideResponseDTO();

        response.setRideId(updatedRide.getId());

        response.setStatus(updatedRide.getStatus());

        response.setMessage("Ride Started");

        return response;
    }

    @Transactional
    public RideResponseDTO completeRide(
            Long rideId
    ) {

        Ride ride =
                rideRepository.findById(rideId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Ride not found"
                                ));

        ride.setStatus(RideStatus.COMPLETED);

        Ride updatedRide =
                rideRepository.save(ride);

        rideEventService.publishRideStatus(
                updatedRide.getId(),
                RideStatus.COMPLETED,
                "Ride Completed"
        );

        RideResponseDTO response =
                new RideResponseDTO();

        response.setRideId(updatedRide.getId());

        response.setStatus(updatedRide.getStatus());

        response.setMessage("Ride Completed");

        return response;
    }

    @Transactional
    public RideResponseDTO cancelRide(
            Long rideId
    ) {

        Ride ride =
                rideRepository.findById(rideId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Ride not found"
                                ));

        ride.setStatus(RideStatus.CANCELLED);

        Ride updatedRide =
                rideRepository.save(ride);

        rideEventService.publishRideStatus(
                updatedRide.getId(),
                RideStatus.CANCELLED,
                "Ride Cancelled"
        );

        RideResponseDTO response =
                new RideResponseDTO();

        response.setRideId(updatedRide.getId());

        response.setStatus(updatedRide.getStatus());

        response.setMessage("Ride Cancelled");

        return response;
    }
}