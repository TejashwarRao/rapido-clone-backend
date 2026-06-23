package com.rapido.analytics_service.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsMetricsService {

    private final Counter etlCounter;
    private final Counter fraudCounter;

    public AnalyticsMetricsService(
            MeterRegistry meterRegistry
    ) {

        this.etlCounter =
                meterRegistry.counter(
                        "analytics_etl_processed"
                );

        this.fraudCounter =
                meterRegistry.counter(
                        "analytics_fraud_detected"
                );
    }

    public void incrementETL() {
        etlCounter.increment();
    }

    public void incrementFraud() {
        fraudCounter.increment();
    }
}