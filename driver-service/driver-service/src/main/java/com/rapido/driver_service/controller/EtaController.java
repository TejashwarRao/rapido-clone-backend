package com.rapido.driver_service.controller;

import com.rapido.driver_service.dto.EtaResponse;
import com.rapido.driver_service.service.EtaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eta")
@RequiredArgsConstructor
public class EtaController {

    private final EtaService etaService;

    @GetMapping("/calculate")
    public ResponseEntity<EtaResponse>
    calculateEta(

            @RequestParam Double distanceKm,

            @RequestParam Boolean traffic,

            @RequestParam Boolean rain
    ) {

        return ResponseEntity.ok(
                etaService.calculateEta(
                        distanceKm,
                        traffic,
                        rain
                )
        );
    }
}
