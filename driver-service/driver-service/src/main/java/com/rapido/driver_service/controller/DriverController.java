package com.rapido.driver_service.controller;

import com.rapido.driver_service.dto.NearbyDriverResponse;
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
}