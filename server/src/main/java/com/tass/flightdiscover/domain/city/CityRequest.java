package com.tass.flightdiscover.domain.city;

import lombok.Data;

@Data
public class CityRequest {
    private Long minPopulation;
    private Long maxPopulation;
    private Long minFlightsNumberFromCity;
    private Long maxFlightsNumberFromCity;
    private Long minFlightsNumberToCity;
    private Long maxFlightsNumberToCity;
    private Long minSrcCitiesNumber;
    private Long maxSrcCitiesNumber;
    private Long minDestCitiesNumber;
    private Long maxDestCitiesNumber;
    private Long minTotalConnectedCitiesNumber;
    private Long maxTotalConnectedCitiesNumber;
    private Long minTotalFlightsToPopulationRatio;
    private Long maxTotalFlightsToPopulationRatio;
    private Sort sortBy;
    private SortOrder sortOrder;
    private Long limit;
}
