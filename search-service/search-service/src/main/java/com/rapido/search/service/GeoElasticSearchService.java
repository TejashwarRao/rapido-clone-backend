package com.rapido.search.service;

import com.rapido.search.document.DriverDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeoElasticSearchService {

    private final ElasticsearchOperations elasticsearchOperations;

}