package com.rapido.driver_service.service;

import com.rapido.driver_service.dto.BestDriverResponse;
import com.rapido.driver_service.dto.NearbyDriverResponse;
import com.rapido.driver_service.dto.RideReassignmentResponse;
import com.rapido.driver_service.entity.Driver;
import com.rapido.driver_service.repository.DriverRepository;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    // =========================================
    // FIND NEARBY DRIVERS
    // =========================================

    public List<NearbyDriverResponse> findNearbyDrivers(
            Double latitude,
            Double longitude,
            Double radius
    ) {

        List<Driver> drivers =
                driverRepository.findAll();

        System.out.println("METHOD EXECUTED");

        System.out.println(
                "TOTAL DRIVERS = "
                        + drivers.size()
        );

        List<NearbyDriverResponse> response =
                new ArrayList<>();

        for (Driver driver : drivers) {

            System.out.println(
                    "Driver Name: "
                            + driver.getName()
            );

            NearbyDriverResponse dto =
                    new NearbyDriverResponse();

            dto.setId(driver.getId());

            dto.setName(driver.getName());

            dto.setLatitude(
                    driver.getLatitude()
            );

            dto.setLongitude(
                    driver.getLongitude()
            );

            dto.setRating(
                    driver.getRating()
            );

            dto.setScore(100.0);

            response.add(dto);
        }

        System.out.println(
                "RESPONSE SIZE = "
                        + response.size()
        );

        return response;
    }

    // =========================================
    // ALLOCATE BEST DRIVER
    // =========================================

    @Transactional
    public BestDriverResponse allocateBestDriver(
            Double latitude,
            Double longitude
    ) {

        List<NearbyDriverResponse> nearbyDrivers =
                findNearbyDrivers(
                        latitude,
                        longitude,
                        50.0
                );

        if (nearbyDrivers.isEmpty()) {

            BestDriverResponse response =
                    new BestDriverResponse();

            response.setMessage(
                    "No drivers available nearby"
            );

            return response;
        }

        NearbyDriverResponse bestDriver =
                nearbyDrivers.get(0);

        Driver lockedDriver =
                driverRepository
                        .findDriverForUpdate(
                                bestDriver.getId()
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Driver not found"
                                )
                        );

        if (lockedDriver.getAvailable() != null
                && !lockedDriver.getAvailable()) {

            BestDriverResponse response =
                    new BestDriverResponse();

            response.setMessage(
                    "Driver already allocated"
            );

            return response;
        }

        lockedDriver.setAvailable(false);

        driverRepository.save(lockedDriver);

        BestDriverResponse response =
                new BestDriverResponse();

        response.setId(lockedDriver.getId());

        response.setName(
                lockedDriver.getName()
        );

        response.setRating(
                lockedDriver.getRating()
        );

        response.setLatitude(
                lockedDriver.getLatitude()
        );

        response.setLongitude(
                lockedDriver.getLongitude()
        );

        response.setMessage(
                "Driver allocated successfully"
        );

        return response;
    }

    // =========================================
    // RIDE REASSIGNMENT
    // =========================================

    @Transactional
    public RideReassignmentResponse reassignDriver(
            Long previousDriverId
    ) {

        Driver previousDriver =
                driverRepository
                        .findDriverForUpdate(
                                previousDriverId
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Previous driver not found"
                                )
                        );

        previousDriver.setAvailable(false);

        driverRepository.save(previousDriver);

        Driver newDriver =
                driverRepository
                        .findByAvailableTrue()
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "No replacement driver available"
                                )
                        );

        if (newDriver.getId().equals(
                previousDriver.getId()
        )) {

            throw new RuntimeException(
                    "No alternate driver available"
            );
        }

        newDriver.setAvailable(false);

        driverRepository.save(newDriver);

        RideReassignmentResponse response =
                new RideReassignmentResponse();

        response.setPreviousDriverId(
                previousDriver.getId()
        );

        response.setPreviousDriverName(
                previousDriver.getName()
        );

        response.setNewDriverId(
                newDriver.getId()
        );

        response.setNewDriverName(
                newDriver.getName()
        );

        response.setMessage(
                "Ride reassigned successfully"
        );

        return response;
    }
}