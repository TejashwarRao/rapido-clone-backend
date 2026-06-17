package com.rapido.search.service;

import com.rapido.search.document.LocationDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationElasticSearchService {

    private final ElasticsearchOperations operations;

}