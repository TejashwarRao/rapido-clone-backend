package com.rapido.search.kafka;

import com.rapido.search.analytics.SearchEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SearchEventConsumer {

    @KafkaListener(
            topics = "search-executed",
            groupId = "search-service"
    )
    public void consume(SearchEvent event) {

        log.info(
                "Analytics Event -> {}",
                event
        );
    }
}