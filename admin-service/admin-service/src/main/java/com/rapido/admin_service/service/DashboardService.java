package com.rapido.admin_service.service;

import com.rapido.admin_service.dto.DashboardSummaryDTO;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    public DashboardSummaryDTO getDashboardSummary() {
        return new DashboardSummaryDTO(
                120L,
                25L,
                80L,
                15000.0,
                5L,
                8L
        );
    }
}