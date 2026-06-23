package com.rapido.analytics_service.ingestion;

import com.rapido.analytics_service.dto.events.RideCompletedEvent;
import com.rapido.analytics_service.warehouse.service.AnalyticsETLService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnalyticsKafkaConsumer {

    private final AnalyticsETLService analyticsETLService;

    @KafkaListener(
            topics = "ride-completed",
            groupId = "analytics-group"
    )
    public void consumeRideCompleted(
            RideCompletedEvent event
    ) {

        log.info(
                "Analytics received ride event {}",
                event.getRideId()
        );

        analyticsETLService.processRide(event);
    }
}