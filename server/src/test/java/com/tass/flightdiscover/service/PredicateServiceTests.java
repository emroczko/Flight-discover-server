package com.tass.flightdiscover.service;

import com.tass.flightdiscover.domain.city.City;
import com.tass.flightdiscover.domain.city.CityRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PredicateServiceTests {

    @Autowired
    PredicateService predicateService;

    @Test
    void testCreatingAllPredicates() {

        var minNumber = 100L;
        var maxNumber = 1000L;

        var cityRequest = CityRequest.builder()
                .maxPopulation(maxNumber)
                .minPopulation(minNumber)
                .maxDestCitiesNumber(maxNumber)
                .minDestCitiesNumber(minNumber)
                .maxSrcCitiesNumber(maxNumber)
                .minSrcCitiesNumber(minNumber)
                .maxFlightsNumberFromCity(maxNumber)
                .minFlightsNumberFromCity(minNumber)
                .maxFlightsNumberToCity(maxNumber)
                .minFlightsNumberToCity(minNumber)
                .maxTotalConnectedCitiesNumber(maxNumber)
                .minTotalConnectedCitiesNumber(minNumber)
                .maxTotalFlightsToPopulationRatio(0.5)
                .minTotalFlightsToPopulationRatio(0.1)
                .build();

        var predicates = predicateService.createPredicatesFromRequest(cityRequest);

        var expectedPredicates = new ArrayList<Predicate<City>>(Collections.emptyList());
        expectedPredicates.add(city -> city.getCityPopulation() >= cityRequest.getMinPopulation());
        expectedPredicates.add(city -> city.getCityPopulation() <= cityRequest.getMaxPopulation());
        expectedPredicates.add(city -> city.getFlightsNumberFromCity() >= cityRequest.getMinFlightsNumberFromCity());
        expectedPredicates.add(city -> city.getFlightsNumberFromCity() <= cityRequest.getMaxFlightsNumberFromCity());
        expectedPredicates.add(city -> city.getFlightsNumberToCity() >= cityRequest.getMinFlightsNumberToCity());
        expectedPredicates.add(city -> city.getFlightsNumberToCity() <= cityRequest.getMaxFlightsNumberToCity());
        expectedPredicates.add(city -> city.getOriginCitiesNumber() >= cityRequest.getMinSrcCitiesNumber());
        expectedPredicates.add(city -> city.getOriginCitiesNumber() <= cityRequest.getMaxSrcCitiesNumber());
        expectedPredicates.add(city -> city.getDestinationCitiesNumber() >= cityRequest.getMinDestCitiesNumber());
        expectedPredicates.add(city -> city.getDestinationCitiesNumber() <= cityRequest.getMaxDestCitiesNumber());
        expectedPredicates.add(city -> city.getTotalConnectedCitiesNumber() >= cityRequest.getMinTotalConnectedCitiesNumber());
        expectedPredicates.add(city -> city.getTotalConnectedCitiesNumber() <= cityRequest.getMinTotalConnectedCitiesNumber());
        expectedPredicates.add(city -> city.getTotalFlightsNumberForCityToPopulationRatio() >= cityRequest.getMinTotalFlightsToPopulationRatio());
        expectedPredicates.add(city -> city.getTotalFlightsNumberForCityToPopulationRatio() <= cityRequest.getMaxTotalFlightsToPopulationRatio());

        assertEquals(predicates.size(), expectedPredicates.size());
    }
}
