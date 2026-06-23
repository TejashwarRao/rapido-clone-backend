package com.rapido.analytics_service.reporting;

import com.rapido.analytics_service.warehouse.repository.FactRideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportingService {

    private final FactRideRepository factRideRepository;

    public String generateCEOReport() {

        Long totalRides =
                factRideRepository.count();

        Double revenue =
                factRideRepository.getTotalRevenue();

        return """
                CEO DAILY REPORT
                
                Total Rides: %d
                
                Total Revenue: %.2f
                """
                .formatted(
                        totalRides,
                        revenue
                );
    }
}