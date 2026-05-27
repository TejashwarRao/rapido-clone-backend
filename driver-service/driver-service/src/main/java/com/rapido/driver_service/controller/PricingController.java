package com.rapido.driver_service.controller;

import com.rapido.driver_service.dto.SurgePricingResponse;
import com.rapido.driver_service.service.PricingService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pricing")
@RequiredArgsConstructor
public class PricingController {

    private final PricingService pricingService;

    @GetMapping("/calculate")
    public ResponseEntity<SurgePricingResponse>
    calculateFare(

            @RequestParam Double distanceKm,

            @RequestParam Integer activeDrivers,

            @RequestParam Integer activeRides,

            @RequestParam Boolean traffic
    ) {

        return ResponseEntity.ok(
                pricingService.calculateFare(
                        distanceKm,
                        activeDrivers,
                        activeRides,
                        traffic
                )
        );
    }
}
