package com.rapido.analytics_service.reporting;

import com.rapido.analytics_service.warehouse.repository.FactRideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CsvReportService {

    private final FactRideRepository factRideRepository;

    public String generateCSV() {

        return """
                metric,value
                total_rides,%d
                total_revenue,%.2f
                """
                .formatted(
                        factRideRepository.count(),
                        factRideRepository.getTotalRevenue()
                );
    }
}