package com.tass.flightdiscover.domain.city;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class City {
    private String name;
    private Double cityLocationY;
    private Double cityLocationX;
    private String countryName;
    private Long cityPopulation;
    private Long flightsNumberFromCity;
    private Long flightsNumberToCity;
    private Long totalFlightsNumber;
    private Long originCitiesNumber;
    private Long destinationCitiesNumber;
    private Long totalConnectedCitiesNumber;
    private Long originCountriesNumber;
    private Long destinationCountriesNumber;
    private Integer busiestMonthFlightsFromCity;
    private Integer busiestMonthFlightsToCity;
    private Double flightsNumberFromCityToPopulationRatio;
    private Double flightsNumberToCityToPopulationRatio;
    private Double totalFlightsNumberForCityToPopulationRatio;
}
