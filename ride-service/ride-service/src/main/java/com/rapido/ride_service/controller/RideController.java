package com.rapido.ride_service.controller;

import com.rapido.ride_service.entity.Ride;
import com.rapido.ride_service.service.RideService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rides")
@RequiredArgsConstructor
public class RideController {

    private final RideService rideService;

    @PostMapping
    public Ride createRide(
            @RequestBody Ride ride
    ) {

        return rideService.createRide(ride);
    }

    @GetMapping
    public List<Ride> getAllRides() {

        return rideService.getAllRides();
    }

    @GetMapping("/{id}")
    public Ride getRide(
            @PathVariable Long id
    ) {

        return rideService.getRideById(id);
    }

    @PutMapping("/{id}/start")
    public Ride startRide(
            @PathVariable Long id
    ) {

        return rideService.startRide(id);
    }

    @PutMapping("/{id}/complete")
    public Ride completeRide(
            @PathVariable Long id
    ) {

        return rideService.completeRide(id);
    }

    @PutMapping("/{id}/cancel")
    public Ride cancelRide(
            @PathVariable Long id
    ) {

        return rideService.cancelRide(id);
    }
}