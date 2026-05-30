package com.rapido.admin_service.service;

import com.rapido.admin_service.dto.FraudSimulationRequestDTO;
import com.rapido.admin_service.entity.FraudAlert;
import com.rapido.admin_service.repository.FraudAlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FraudDetectionService {

    private final FraudAlertRepository fraudAlertRepository;

    public FraudAlert simulateFraud(FraudSimulationRequestDTO request) {

        FraudAlert fraudAlert = new FraudAlert();

        fraudAlert.setUserId(request.getUserId());
        fraudAlert.setSeverity(request.getSeverity());
        fraudAlert.setDescription(request.getDescription());
        fraudAlert.setResolved(false);

        return fraudAlertRepository.save(fraudAlert);
    }
}