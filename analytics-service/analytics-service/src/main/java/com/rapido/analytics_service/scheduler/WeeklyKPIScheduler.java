package com.rapido.analytics_service.scheduler;

import com.rapido.analytics_service.warehouse.entity.WeeklyKPI;
import com.rapido.analytics_service.warehouse.repository.FactRideRepository;
import com.rapido.analytics_service.warehouse.repository.WeeklyKPIRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class WeeklyKPIScheduler {

    private final FactRideRepository factRideRepository;
    private final WeeklyKPIRepository weeklyKPIRepository;

    @Scheduled(cron = "0 */3 * * * ?")
    public void generateWeeklyKPI() {

        WeeklyKPI kpi = WeeklyKPI.builder()
                .weekStart(LocalDate.now())
                .totalRides(factRideRepository.count())
                .completedRides(
                        factRideRepository.countByStatus("COMPLETED")
                )
                .cancelledRides(
                        factRideRepository.countByStatus("CANCELLED")
                )
                .totalRevenue(
                        factRideRepository.getTotalRevenue()
                )
                .topCity(
                        factRideRepository.getTopCity()
                )
                .createdAt(LocalDateTime.now())
                .build();

        weeklyKPIRepository.save(kpi);

        log.info("Weekly KPI generated successfully");
    }
}