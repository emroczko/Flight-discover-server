package com.tass.flightdiscover.facades;

import com.tass.flightdiscover.domain.city.City;
import com.tass.flightdiscover.domain.city.CityRequest;
import com.tass.flightdiscover.domain.city.SortOrder;
import com.tass.flightdiscover.exceptions.CityNotFoundException;
import com.tass.flightdiscover.service.CityService;
import com.tass.flightdiscover.service.PredicateService;
import com.tass.flightdiscover.service.SortingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class CityFacade {
    private final CityService cityService;
    private final SortingService sortingService;
    private final PredicateService predicateService;

    public List<City> getCitiesByName(String name) throws CityNotFoundException {
        return throwIfEmpty(cityService.getCityByName(name));
    }

    public List<City> getCities(CityRequest request) throws CityNotFoundException {

        var sort = request.getSortBy();
        var sortOrder = request.getSortOrder() == null ? SortOrder.ASC : request.getSortOrder();
        var limit = request.getLimit();
        var predicates = predicateService.createPredicatesFromRequest(request);
        var cities = cityService.getAllCities()
                .stream()
                .filter(predicates.stream().reduce(x -> true, Predicate::and))
                .toList();

        if (sort != null) {
            cities = switch (sort) {
                case POPULATION -> sortingService.sortByPopulation(cities, sortOrder);
                case CONNECTED_CITIES -> sortingService.sortByConnectedCitiesNumber(cities, sortOrder);
                case FLIGHTS_NUMBER -> sortingService.sortByFlightsNumber(cities, sortOrder);
                case FLIGHTS_TO_POPULATION -> sortingService.sortByFlightsToPopulationRatio(cities, sortOrder);
            };
        }

        cities = limit != null ? cities.stream().limit(limit).toList() : cities;

        return throwIfEmpty(cities);
    }

    private <T> List<T> throwIfEmpty(List<T> list) throws CityNotFoundException {
        if (list.isEmpty()) {
            throw new CityNotFoundException("No cities found for given parameters!");
        }
        return list;
    }
}
