package com.rapido.ride_service.controller;

import com.rapido.ride_service.dto.RideRequestDTO;
import com.rapido.ride_service.service.RideService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rides")
public class RideController {

    private final RideService rideService;

    public RideController(
            RideService rideService
    ) {

        this.rideService = rideService;
    }

    @PostMapping("/request")
    public ResponseEntity<?> requestRide(
            @Valid @RequestBody RideRequestDTO dto
    ) {

        Long userId = 1L;

        return ResponseEntity.ok(
                rideService.requestRide(
                        userId,
                        dto
                )
        );
    }

    @PutMapping("/{rideId}/accept")
    public ResponseEntity<?> acceptRide(
            @PathVariable Long rideId
    ) {

        return ResponseEntity.ok(
                rideService.acceptRide(rideId)
        );
    }

    @PutMapping("/{rideId}/start")
    public ResponseEntity<?> startRide(
            @PathVariable Long rideId
    ) {

        return ResponseEntity.ok(
                rideService.startRide(rideId)
        );
    }

    @PutMapping("/{rideId}/complete")
    public ResponseEntity<?> completeRide(
            @PathVariable Long rideId
    ) {

        return ResponseEntity.ok(
                rideService.completeRide(rideId)
        );
    }

    @PutMapping("/{rideId}/cancel")
    public ResponseEntity<?> cancelRide(
            @PathVariable Long rideId
    ) {

        return ResponseEntity.ok(
                rideService.cancelRide(rideId)
        );
    }
}