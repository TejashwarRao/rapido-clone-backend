package com.rapido.driver_service.controller;

import com.rapido.driver_service.dto.BestDriverResponse;
import com.rapido.driver_service.dto.NearbyDriverResponse;
import com.rapido.driver_service.entity.Driver;
import com.rapido.driver_service.repository.DriverRepository;
import com.rapido.driver_service.service.DriverService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    private final DriverRepository driverRepository;

    // TEST DATABASE DATA

    @GetMapping("/test")
    public List<Driver> testDrivers() {

        return driverRepository.findAll();
    }

    // FIND NEARBY DRIVERS

    @GetMapping("/nearby")
    public ResponseEntity<List<NearbyDriverResponse>>
    findNearbyDrivers(
            @RequestParam Double latitude,
            @RequestParam Double longitude
    ) {

        return ResponseEntity.ok(
                driverService.findNearbyDrivers(
                        latitude,
                        longitude,
                        50.0
                )
        );
    }

    // ALLOCATE BEST DRIVER

    @GetMapping("/allocate")
    public ResponseEntity<BestDriverResponse>
    allocateBestDriver(
            @RequestParam Double latitude,
            @RequestParam Double longitude
    ) {

        return ResponseEntity.ok(
                driverService.allocateBestDriver(
                        latitude,
                        longitude
                )
        );
    }
}