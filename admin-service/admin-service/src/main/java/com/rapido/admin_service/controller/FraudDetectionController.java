package com.rapido.admin_service.controller;

import com.rapido.admin_service.dto.FraudSimulationRequestDTO;
import com.rapido.admin_service.entity.FraudAlert;
import com.rapido.admin_service.service.FraudDetectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/fraud")
@RequiredArgsConstructor
public class FraudDetectionController {

    private final FraudDetectionService fraudDetectionService;

    @PostMapping("/simulate")
    public ResponseEntity<FraudAlert> simulateFraud(
            @RequestBody FraudSimulationRequestDTO request) {

        return ResponseEntity.ok(
                fraudDetectionService.simulateFraud(request)
        );
    }
}