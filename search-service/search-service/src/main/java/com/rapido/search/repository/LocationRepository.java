package com.rapido.search.repository;

import com.rapido.search.document.LocationDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository
        extends ElasticsearchRepository<LocationDocument, Long> {

    List<LocationDocument> findByNameContainingIgnoreCase(String keyword);
}