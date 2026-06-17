package com.rapido.search.repository;

import com.rapido.search.document.DriverDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverSearchRepository
        extends ElasticsearchRepository<DriverDocument, Long> {
}