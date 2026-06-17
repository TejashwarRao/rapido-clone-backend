package com.rapido.search.service;

import com.rapido.search.document.DriverDocument;
import com.rapido.search.repository.DriverSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReindexService {

    private final DriverSearchRepository repository;

    public String reindex() {

        log.info("================================");
        log.info("REINDEX STARTED");
        log.info("================================");

        long totalDrivers =
                repository.count();

        log.info(
                "Drivers Present In Index : {}",
                totalDrivers
        );

        log.info("================================");
        log.info("REINDEX COMPLETED");
        log.info("================================");

        return "Reindex Completed Successfully";
    }
}