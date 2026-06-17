package com.rapido.search.controller;

import com.rapido.search.document.DriverDocument;
import com.rapido.search.service.BulkIndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class BulkIndexController {

    private final BulkIndexService service;

    @PostMapping("/bulk-index")
    public String bulkIndex(
            @RequestBody List<DriverDocument> drivers) {

        service.bulkIndex(drivers);

        return "Bulk Index Completed Successfully";
    }
}