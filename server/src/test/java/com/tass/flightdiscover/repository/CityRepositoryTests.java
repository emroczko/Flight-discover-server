package com.tass.flightdiscover.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CityRepositoryTests {

    @Autowired
    private CityRepository cityRepository;

    @Test
    void testGetCityByName() {
        var foundCities = cityRepository.findCityByName("New York");

        assertFalse(foundCities.isEmpty());
        var foundCity = foundCities.get(0);

        assertAll(() -> {
            assertEquals("New York", foundCity.getName());
            assertEquals("United States", foundCity.getCountryName());
            assertEquals(40.6943, foundCity.getCityLocationY());
            assertEquals(-73.9249, foundCity.getCityLocationX());
            assertEquals(19354922L, foundCity.getCityPopulation());
            assertEquals(183503L, foundCity.getFlightsNumberToCity());
            assertEquals(183449L, foundCity.getFlightsNumberFromCity());
            assertEquals(86L, foundCity.getOriginCitiesNumber());
            assertEquals(84L, foundCity.getDestinationCitiesNumber());
            assertEquals(86L, foundCity.getTotalConnectedCitiesNumber());
            assertEquals(2, foundCity.getOriginCountriesNumber());
            assertEquals(2, foundCity.getDestinationCountriesNumber());
            assertEquals(3, foundCity.getBusiestMonthFlightsToCity());
            assertEquals(3, foundCity.getBusiestMonthFlightsFromCity());
            assertEquals(0.009478157545662029, foundCity.getFlightsNumberFromCityToPopulationRatio());
            assertEquals(0.009480947533655781, foundCity.getFlightsNumberToCityToPopulationRatio());
            assertEquals(0.01895910507931781, foundCity.getTotalFlightsNumberForCityToPopulationRatio());
        });
    }

}
