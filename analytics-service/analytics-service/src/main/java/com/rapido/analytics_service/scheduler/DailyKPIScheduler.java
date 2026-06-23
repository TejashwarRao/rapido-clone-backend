package com.rapido.analytics_service.scheduler;

import com.rapido.analytics_service.warehouse.entity.DailyKPI;
import com.rapido.analytics_service.warehouse.repository.DailyKPIRepository;
import com.rapido.analytics_service.warehouse.repository.FactRideRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class DailyKPIScheduler {

    private final FactRideRepository factRideRepository;

    private final DailyKPIRepository dailyKPIRepository;

    @Scheduled(cron = "0 */2 * * * ?")
    public void generateDailyKPI() {

        Long totalRides =
                factRideRepository.count();

        Long completedRides =
                factRideRepository.countByStatus(
                        "COMPLETED"
                );

        Long cancelledRides =
                factRideRepository.countByStatus(
                        "CANCELLED"
                );

        Double revenue =
                factRideRepository.getTotalRevenue();

        DailyKPI kpi =
                DailyKPI.builder()
                        .reportDate(LocalDate.now())
                        .totalRides(totalRides)
                        .completedRides(completedRides)
                        .cancelledRides(cancelledRides)
                        .totalRevenue(revenue)
                        .createdAt(LocalDateTime.now())
                        .build();

        dailyKPIRepository.save(kpi);

        log.info(
                "Daily KPI generated successfully"
        );
    }
}