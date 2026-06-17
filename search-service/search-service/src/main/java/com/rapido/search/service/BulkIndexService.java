package com.rapido.search.service;

import com.rapido.search.document.DriverDocument;
import com.rapido.search.repository.DriverSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BulkIndexService {

    private final DriverSearchRepository repository;

    public void bulkIndex(List<DriverDocument> drivers) {

        log.info("Starting Bulk Indexing. Total Drivers: {}", drivers.size());

        repository.saveAll(drivers);

        log.info("Bulk Indexing Completed Successfully");
    }
}