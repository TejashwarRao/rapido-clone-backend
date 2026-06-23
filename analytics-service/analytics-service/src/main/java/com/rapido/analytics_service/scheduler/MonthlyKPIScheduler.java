package com.rapido.analytics_service.scheduler;

import com.rapido.analytics_service.warehouse.entity.AnalyticsJobAudit;
import com.rapido.analytics_service.warehouse.entity.MonthlyKPI;
import com.rapido.analytics_service.warehouse.repository.AnalyticsJobAuditRepository;
import com.rapido.analytics_service.warehouse.repository.FactRideRepository;
import com.rapido.analytics_service.warehouse.repository.MonthlyKPIRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;

@Component
@RequiredArgsConstructor
@Slf4j
public class MonthlyKPIScheduler {

    private final FactRideRepository factRideRepository;

    private final MonthlyKPIRepository monthlyKPIRepository;

    private final AnalyticsJobAuditRepository analyticsJobAuditRepository;

    @Scheduled(cron = "0 */5 * * * ?")
    public void generateMonthlyKPI() {

        LocalDateTime startTime = LocalDateTime.now();

        try {

            MonthlyKPI kpi = MonthlyKPI.builder()
                    .monthName(
                            Month.from(LocalDateTime.now()).name()
                    )
                    .totalRides(
                            factRideRepository.count()
                    )
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

            monthlyKPIRepository.save(kpi);

            AnalyticsJobAudit audit =
                    AnalyticsJobAudit.builder()
                            .jobName("MONTHLY_KPI_JOB")
                            .startTime(startTime)
                            .endTime(LocalDateTime.now())
                            .recordsProcessed(
                                    factRideRepository.count()
                            )
                            .failures(0L)
                            .build();

            analyticsJobAuditRepository.save(audit);

            log.info("Monthly KPI generated successfully");

        } catch (Exception ex) {

            AnalyticsJobAudit audit =
                    AnalyticsJobAudit.builder()
                            .jobName("MONTHLY_KPI_JOB")
                            .startTime(startTime)
                            .endTime(LocalDateTime.now())
                            .recordsProcessed(0L)
                            .failures(1L)
                            .build();

            analyticsJobAuditRepository.save(audit);

            log.error("Monthly KPI generation failed", ex);
        }
    }
}