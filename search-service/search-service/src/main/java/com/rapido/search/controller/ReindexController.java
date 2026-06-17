package com.rapido.search.controller;

import com.rapido.search.service.ReindexService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ReindexController {

    private final ReindexService service;

    @PostMapping("/reindex")
    public String reindex() {

        return service.reindex();
    }
}