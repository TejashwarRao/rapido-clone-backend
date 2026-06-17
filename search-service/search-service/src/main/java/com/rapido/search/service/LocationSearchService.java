package com.rapido.search.service;

import com.rapido.search.document.LocationDocument;
import com.rapido.search.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationSearchService {

    private final LocationRepository repository;

    public List<LocationDocument> search(String keyword) {

        return repository.findByNameContainingIgnoreCase(keyword);
    }
}