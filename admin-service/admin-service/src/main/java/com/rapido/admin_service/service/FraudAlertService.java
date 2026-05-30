package com.rapido.admin_service.service;

import com.rapido.admin_service.entity.FraudAlert;
import com.rapido.admin_service.repository.FraudAlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FraudAlertService {

    private final FraudAlertRepository fraudAlertRepository;

    public FraudAlert createFraudAlert(FraudAlert fraudAlert) {

        fraudAlert.setResolved(false);
        fraudAlert.setCreatedAt(LocalDateTime.now());

        return fraudAlertRepository.save(fraudAlert);
    }

    public List<FraudAlert> getFraudAlerts(String severity, Boolean resolved) {

        if (severity != null && !severity.isEmpty()) {
            return fraudAlertRepository.findBySeverity(severity);
        }

        if (resolved != null) {
            return fraudAlertRepository.findByResolved(resolved);
        }

        return fraudAlertRepository.findAll();
    }
}