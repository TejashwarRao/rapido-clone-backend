package com.rapido.admin_service.controller;

import com.rapido.admin_service.entity.FraudAlert;
import com.rapido.admin_service.service.FraudAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/fraud")
@RequiredArgsConstructor
public class FraudAlertController {

    private final FraudAlertService fraudAlertService;

    @PostMapping("/alerts")
    public ResponseEntity<FraudAlert> createFraudAlert(@RequestBody FraudAlert fraudAlert) {
        return ResponseEntity.ok(fraudAlertService.createFraudAlert(fraudAlert));
    }

    @GetMapping("/alerts")
    public ResponseEntity<List<FraudAlert>> getFraudAlerts(
            @RequestParam(required = false) String severity,
            @RequestParam(required = false) Boolean resolved) {

        return ResponseEntity.ok(
                fraudAlertService.getFraudAlerts(severity, resolved)
        );
    }
}