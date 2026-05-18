package com.rapido.driver_service.controller;

import com.rapido.driver_service.dto.DriverProfileDTO;
import com.rapido.driver_service.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/driver/profile")
    public ResponseEntity<DriverProfileDTO> getDriverProfile() {
        return ResponseEntity.ok(driverService.getProfile("driver@example.com"));
    }

    @PutMapping("/driver/profile")
    public ResponseEntity<String> updateDriverProfile(@Valid @RequestBody DriverProfileDTO dto) {
        driverService.updateProfile(dto.getEmail(), dto);
        return ResponseEntity.ok("Driver Profile Updated");
    }

    @PutMapping("/driver/availability")
    public ResponseEntity<String> updateAvailability(@RequestParam Boolean available) {
        driverService.updateAvailability("driver@example.com", available);
        return ResponseEntity.ok("Availability Updated");
    }

    @PutMapping("/driver/online-status")
    public ResponseEntity<String> updateOnlineStatus(@RequestParam Boolean online) {
        driverService.updateOnlineStatus("driver@example.com", online);
        return ResponseEntity.ok("Online Status Updated");
    }

    @PutMapping("/driver/location")
    public ResponseEntity<String> updateLocation(
            @RequestParam Double latitude,
            @RequestParam Double longitude
    ) {
        driverService.updateLocation("driver@example.com", latitude, longitude);
        return ResponseEntity.ok("Location Updated");
    }
}