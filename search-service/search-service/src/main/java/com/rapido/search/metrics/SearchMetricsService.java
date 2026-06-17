package com.rapido.search.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class SearchMetricsService {

    private final Counter searchCounter;

    public SearchMetricsService(MeterRegistry registry) {

        this.searchCounter =
                registry.counter(
                        "search.requests.total"
                );
    }

    public void incrementSearches() {

        searchCounter.increment();
    }
}