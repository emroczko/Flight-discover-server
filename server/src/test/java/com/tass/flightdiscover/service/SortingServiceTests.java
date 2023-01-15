package com.tass.flightdiscover.service;

import com.tass.flightdiscover.domain.city.City;
import com.tass.flightdiscover.domain.city.SortOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SortingServiceTests {

    private static List<City> cities;
    @Autowired
    private SortingService sortingService;

    @BeforeAll
    static void prepareData() {
        cities = List.of(City.builder().totalConnectedCitiesNumber(1000L)
                        .cityPopulation(4000L)
                        .totalFlightsNumberForCityToPopulationRatio(0.1)
                        .totalFlightsNumber(50L).build(),
                City.builder().totalConnectedCitiesNumber(20000L)
                        .cityPopulation(60000L)
                        .totalFlightsNumberForCityToPopulationRatio(0.15)
                        .totalFlightsNumber(7000L).build(),
                City.builder().totalConnectedCitiesNumber(300000L)
                        .cityPopulation(8000L)
                        .totalFlightsNumberForCityToPopulationRatio(0.05)
                        .totalFlightsNumber(9L).build());
    }

    @Test
    void testSortByConnectedCitiesNumberDesc() {
        var sorted = sortingService.sortByConnectedCitiesNumber(cities, SortOrder.DESC);

        assertEquals(3, sorted.size());
        assertEquals(300000L, sorted.get(0).getTotalConnectedCitiesNumber());
        assertEquals(20000L, sorted.get(1).getTotalConnectedCitiesNumber());
        assertEquals(1000L, sorted.get(2).getTotalConnectedCitiesNumber());
    }

    @Test
    void testSortByConnectedCitiesNumberAsc() {
        var sorted = sortingService.sortByConnectedCitiesNumber(cities, SortOrder.ASC);

        assertEquals(300000L, sorted.get(2).getTotalConnectedCitiesNumber());
        assertEquals(20000L, sorted.get(1).getTotalConnectedCitiesNumber());
        assertEquals(1000L, sorted.get(0).getTotalConnectedCitiesNumber());
    }

    @Test
    void testSortByPopulationDesc() {
        var sorted = sortingService.sortByPopulation(cities, SortOrder.DESC);

        assertEquals(3, sorted.size());
        assertEquals(60000L, sorted.get(0).getCityPopulation());
        assertEquals(8000L, sorted.get(1).getCityPopulation());
        assertEquals(4000L, sorted.get(2).getCityPopulation());
    }

    @Test
    void testSortByPopulationAsc() {
        var sorted = sortingService.sortByPopulation(cities, SortOrder.ASC);

        assertEquals(60000L, sorted.get(2).getCityPopulation());
        assertEquals(8000L, sorted.get(1).getCityPopulation());
        assertEquals(4000L, sorted.get(0).getCityPopulation());
    }

    @Test
    void testSortByFlightsToPopulationRatioDesc() {
        var sorted = sortingService.sortByFlightsToPopulationRatio(cities, SortOrder.DESC);

        assertEquals(3, sorted.size());
        assertEquals(0.15, sorted.get(0).getTotalFlightsNumberForCityToPopulationRatio());
        assertEquals(0.1, sorted.get(1).getTotalFlightsNumberForCityToPopulationRatio());
        assertEquals(0.05, sorted.get(2).getTotalFlightsNumberForCityToPopulationRatio());
    }

    @Test
    void testSortByFlightsToPopulationRatioAsc() {
        var sorted = sortingService.sortByFlightsToPopulationRatio(cities, SortOrder.ASC);

        assertEquals(0.15, sorted.get(2).getTotalFlightsNumberForCityToPopulationRatio());
        assertEquals(0.1, sorted.get(1).getTotalFlightsNumberForCityToPopulationRatio());
        assertEquals(0.05, sorted.get(0).getTotalFlightsNumberForCityToPopulationRatio());
    }

    @Test
    void testSortByFlightsNumberDesc() {
        var sorted = sortingService.sortByFlightsNumber(cities, SortOrder.DESC);

        assertEquals(3, sorted.size());
        assertEquals(7000L, sorted.get(0).getTotalFlightsNumber());
        assertEquals(50L, sorted.get(1).getTotalFlightsNumber());
        assertEquals(9L, sorted.get(2).getTotalFlightsNumber());
    }

    @Test
    void testSortByFlightsNumberAsc() {
        var sorted = sortingService.sortByFlightsNumber(cities, SortOrder.ASC);

        assertEquals(7000L, sorted.get(2).getTotalFlightsNumber());
        assertEquals(50L, sorted.get(1).getTotalFlightsNumber());
        assertEquals(9L, sorted.get(0).getTotalFlightsNumber());
    }
}
