package com.rapido.ride_service.controller;

import com.rapido.ride_service.entity.DriverLocationEntity;
import com.rapido.ride_service.event.DriverLocationEvent;
import com.rapido.ride_service.event.RideStatusEvent;
import com.rapido.ride_service.service.TrackingService;
import com.rapido.ride_service.tracker.DriverMovementSimulator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracking")
public class TrackingController {

    private final TrackingService trackingService;
    private final DriverMovementSimulator driverMovementSimulator;

    public TrackingController(
            TrackingService trackingService,
            DriverMovementSimulator driverMovementSimulator) {

        this.trackingService = trackingService;
        this.driverMovementSimulator = driverMovementSimulator;
    }

    @PostMapping("/driver/location/live")
    public ResponseEntity<String> updateLiveLocation(
            @RequestBody DriverLocationEvent event) {

        trackingService.processLocation(event);

        return ResponseEntity.ok("Location Updated Successfully");
    }

    @PostMapping("/ride/status")
    public ResponseEntity<String> sendRideStatus(
            @RequestBody RideStatusEvent event) {

        trackingService.sendRideStatusEvent(event);

        return ResponseEntity.ok("Ride Status Event Sent Successfully");
    }

    @PostMapping("/start-simulation")
    public ResponseEntity<String> startSimulation(
            @RequestParam Long rideId,
            @RequestParam Long driverId) {

        driverMovementSimulator.startSimulation(rideId, driverId);

        return ResponseEntity.ok("Driver Movement Simulation Started");
    }

    @GetMapping("/history/{rideId}")
    public ResponseEntity<List<DriverLocationEntity>> getRideTrackingHistory(
            @PathVariable Long rideId) {

        return ResponseEntity.ok(
                trackingService.getRideTrackingHistory(rideId)
        );
    }
}