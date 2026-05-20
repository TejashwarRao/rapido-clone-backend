package com.rapido.ride_service.controller;

import com.rapido.ride_service.entity.Ride;

import com.rapido.ride_service.service.RideService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rides")
public class RideController {

    private final RideService rideService;

    public RideController(
            RideService rideService
    ) {

        this.rideService =
                rideService;
    }

    // TEST API
    @GetMapping("/test")
    public String test() {

        return "Ride Service Working";
    }

    // CREATE RIDE
    @PostMapping
    public Ride createRide(
            @RequestBody Ride ride
    ) {

        return rideService.createRide(
                ride
        );
    }

    // GET ALL RIDES
    @GetMapping
    public List<Ride> getAllRides() {

        return rideService.getAllRides();
    }

    // START RIDE
    @PutMapping("/start/{rideId}")
    public Ride startRide(
            @PathVariable Long rideId
    ) {

        return rideService.startRide(
                rideId
        );
    }

    // COMPLETE RIDE
    @PutMapping("/complete/{rideId}")
    public Ride completeRide(
            @PathVariable Long rideId
    ) {

        return rideService.completeRide(
                rideId
        );
    }

    // CANCEL RIDE
    @PutMapping("/cancel/{rideId}")
    public Ride cancelRide(
            @PathVariable Long rideId
    ) {

        return rideService.cancelRide(
                rideId
        );
    }
}