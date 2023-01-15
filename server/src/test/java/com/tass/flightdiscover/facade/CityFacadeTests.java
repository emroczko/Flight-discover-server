package com.tass.flightdiscover.facade;

import com.tass.flightdiscover.domain.city.CityRequest;
import com.tass.flightdiscover.domain.city.Sort;
import com.tass.flightdiscover.domain.city.SortOrder;
import com.tass.flightdiscover.exceptions.CityNotFoundException;
import com.tass.flightdiscover.facades.CityFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CityFacadeTests {

    @Autowired
    private CityFacade cityFacade;

    @Test
    void testGetCityByName() throws CityNotFoundException {
        var cityName = "New York";
        var foundCities = cityFacade.getCitiesByName(cityName);
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

    @Test
    void testGetCityByNameWhenNotFound() {
        var exception = assertThrows(CityNotFoundException.class,
                () -> cityFacade.getCitiesByName("TESTESTEST"));

        var expectedMessage = "No cities found for given parameters!";
        var actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetCities() throws CityNotFoundException {
        var cityRequest = CityRequest.builder()
                .minPopulation(10000000L)
                .sortBy(Sort.POPULATION)
                .sortOrder(SortOrder.DESC)
                .build();

        var foundCities = cityFacade.getCities(cityRequest);

        assertEquals(2, foundCities.size());

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

        assertEquals("Los Angeles", foundCities.get(1).getName());
    }

    @Test
    void testGetCitiesNotFound() {
        var cityRequest = CityRequest.builder()
                .minPopulation(30000000L)
                .sortBy(Sort.POPULATION)
                .sortOrder(SortOrder.DESC)
                .build();

        var exception = assertThrows(CityNotFoundException.class,
                () -> cityFacade.getCities(cityRequest));

        var expectedMessage = "No cities found for given parameters!";
        var actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
