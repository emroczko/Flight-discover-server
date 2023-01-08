package com.tass.flightdiscover.service;

import com.tass.flightdiscover.domain.city.City;
import com.tass.flightdiscover.domain.city.CityRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Service
public class PredicateService {
    public List<Predicate<City>> createPredicatesFromRequest(CityRequest request) {
        var predicates = new ArrayList<Predicate<City>>(Collections.emptyList());

        if (request.getMinPopulation() != null) {
            predicates.add(city -> city.getCityPopulation() >= request.getMinPopulation());
        }

        if (request.getMaxPopulation() != null) {
            predicates.add(city -> city.getCityPopulation() <= request.getMaxPopulation());
        }

        if (request.getMinFlightsNumberFromCity() != null) {
            predicates.add(city -> city.getFlightsNumberFromCity() >= request.getMinFlightsNumberFromCity());
        }

        if (request.getMaxFlightsNumberFromCity() != null) {
            predicates.add(city -> city.getFlightsNumberFromCity() <= request.getMaxFlightsNumberFromCity());
        }

        if (request.getMinFlightsNumberToCity() != null) {
            predicates.add(city -> city.getFlightsNumberToCity() >= request.getMinFlightsNumberToCity());
        }

        if (request.getMaxFlightsNumberToCity() != null) {
            predicates.add(city -> city.getFlightsNumberToCity() <= request.getMaxFlightsNumberToCity());
        }

        if (request.getMinSrcCitiesNumber() != null) {
            predicates.add(city -> city.getOriginCitiesNumber() >= request.getMinSrcCitiesNumber());
        }

        if (request.getMaxSrcCitiesNumber() != null) {
            predicates.add(city -> city.getOriginCitiesNumber() <= request.getMaxSrcCitiesNumber());
        }

        if (request.getMinDestCitiesNumber() != null) {
            predicates.add(city -> city.getDestinationCitiesNumber() >= request.getMinDestCitiesNumber());
        }

        if (request.getMaxDestCitiesNumber() != null) {
            predicates.add(city -> city.getDestinationCitiesNumber() <= request.getMaxDestCitiesNumber());
        }

        if (request.getMinTotalConnectedCitiesNumber() != null) {
            predicates.add(city -> city.getTotalConnectedCitiesNumber() >= request.getMinTotalConnectedCitiesNumber());
        }

        if (request.getMaxTotalConnectedCitiesNumber() != null) {
            predicates.add(city -> city.getTotalConnectedCitiesNumber() <= request.getMinTotalConnectedCitiesNumber());
        }

        if (request.getMinTotalFlightsToPopulationRatio() != null) {
            predicates.add(city -> city.getTotalFlightsNumberForCityToPopulationRatio() >= request.getMinTotalFlightsToPopulationRatio());
        }

        if (request.getMaxTotalFlightsToPopulationRatio() != null) {
            predicates.add(city -> city.getTotalFlightsNumberForCityToPopulationRatio() <= request.getMaxTotalFlightsToPopulationRatio());
        }

        return predicates;
    }
}
