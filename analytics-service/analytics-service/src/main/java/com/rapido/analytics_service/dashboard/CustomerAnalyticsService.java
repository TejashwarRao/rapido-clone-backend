package com.rapido.analytics_service.dashboard;

import com.rapido.analytics_service.warehouse.entity.CustomerAnalytics;
import com.rapido.analytics_service.warehouse.repository.CustomerAnalyticsRepository;
import com.rapido.analytics_service.warehouse.repository.FactRideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerAnalyticsService {

    private final FactRideRepository factRideRepository;
    private final CustomerAnalyticsRepository customerAnalyticsRepository;

    public CustomerAnalytics generateAnalytics() {

        Long totalCustomers =
                factRideRepository.getTotalCustomers();

        Long returningCustomers =
                (long) factRideRepository
                        .getReturningCustomers()
                        .size();

        Long newCustomers =
                totalCustomers - returningCustomers;

        Double retentionRate =
                totalCustomers == 0
                        ? 0
                        : (returningCustomers * 100.0)
                          / totalCustomers;

        Double churnRate =
                100.0 - retentionRate;

        CustomerAnalytics analytics =
                CustomerAnalytics.builder()
                        .reportDate(LocalDate.now())
                        .newCustomers(newCustomers)
                        .returningCustomers(returningCustomers)
                        .retentionRate(retentionRate)
                        .churnRate(churnRate)
                        .createdAt(LocalDateTime.now())
                        .build();

        return customerAnalyticsRepository.save(
                analytics
        );
    }
}