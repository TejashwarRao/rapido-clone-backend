package com.rapido.search.analytics;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(SearchEvent event) {

        kafkaTemplate.send(
                "search-executed",
                event
        );
    }
}