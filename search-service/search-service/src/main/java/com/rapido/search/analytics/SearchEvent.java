package com.rapido.search.analytics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchEvent {

    private String searchTerm;

    private Long userId;

    private String selectedResult;

    private Long responseTime;
}