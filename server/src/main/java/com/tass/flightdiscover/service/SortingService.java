package com.tass.flightdiscover.service;

import com.tass.flightdiscover.domain.city.City;
import com.tass.flightdiscover.domain.city.SortOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortingService {

    public List<City> sortByConnectedCitiesNumber(List<City> cities, SortOrder order) {
        return cities.stream()
                .sorted((c1, c2) -> switch (order) {
                    case ASC -> (int) (c1.getTotalConnectedCitiesNumber() - c2.getTotalConnectedCitiesNumber());
                    case DESC -> (int) (c2.getTotalConnectedCitiesNumber() - c1.getTotalConnectedCitiesNumber());
                })
                .toList();
    }

    public List<City> sortByPopulation(List<City> cities, SortOrder order) {
        return cities.stream()
                .sorted((c1, c2) -> switch (order) {
                    case ASC -> (int) (c1.getCityPopulation() - c2.getCityPopulation());
                    case DESC -> (int) (c2.getCityPopulation() - c1.getCityPopulation());
                })
                .toList();
    }

    public List<City> sortByFlightsToPopulationRatio(List<City> cities, SortOrder order) {
        return cities.stream()
                .sorted((c1, c2) -> switch (order) {
                    case ASC ->
                            Double.compare(c1.getTotalFlightsNumberForCityToPopulationRatio(), c2.getTotalFlightsNumberForCityToPopulationRatio());
                    case DESC ->
                            Double.compare(c2.getTotalFlightsNumberForCityToPopulationRatio(), c1.getTotalFlightsNumberForCityToPopulationRatio());
                })
                .toList();
    }

    public List<City> sortByFlightsNumber(List<City> cities, SortOrder order) {
        return cities.stream()
                .sorted((c1, c2) -> switch (order) {
                    case ASC -> (int) (c1.getTotalFlightsNumber() - c2.getTotalFlightsNumber());
                    case DESC -> (int) (c2.getTotalFlightsNumber() - c1.getTotalFlightsNumber());
                })
                .toList();
    }
}
