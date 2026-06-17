package com.rapido.search.controller;

import com.rapido.search.document.LocationDocument;
import com.rapido.search.repository.LocationRepository;
import com.rapido.search.service.LocationSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class LocationController {

    private final LocationSearchService service;

    private final LocationRepository repository;

    @PostMapping("/seed-locations")
    public String seed() {

        repository.save(
                LocationDocument.builder()
                        .id(1L)
                        .name("Hyderabad Airport")
                        .type("AIRPORT")
                        .build());

        repository.save(
                LocationDocument.builder()
                        .id(2L)
                        .name("Hyderabad Railway Station")
                        .type("RAILWAY")
                        .build());

        repository.save(
                LocationDocument.builder()
                        .id(3L)
                        .name("Hyderabad Hitech City")
                        .type("IT_HUB")
                        .build());

        return "Locations Seeded Successfully";
    }

    @GetMapping("/autocomplete")
    public List<LocationDocument> autocomplete(
            @RequestParam String keyword) {

        return service.search(keyword);
    }
    @GetMapping("/fuzzy")
    public String fuzzy(
            @RequestParam String keyword) {

        return "Searching for : " + keyword;
    }
}