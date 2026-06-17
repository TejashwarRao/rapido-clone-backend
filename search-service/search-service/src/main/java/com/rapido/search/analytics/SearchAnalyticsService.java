package com.rapido.search.analytics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SearchAnalyticsService {

    public void track(SearchEvent event) {

        log.info(
                "Search Event -> {}",
                event
        );
    }
}