package com.tass.flightdiscover.service;

import com.tass.flightdiscover.domain.city.City;
import com.tass.flightdiscover.domain.city.CityEntity;
import com.tass.flightdiscover.repository.CityRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CityServiceTests {

    private static List<City> cities;
    private static List<CityEntity> cityEntities;
    @MockBean
    CityRepository cityRepository;
    @Autowired
    CityService cityService;

    @BeforeAll
    static void prepareData() {
        cityEntities = List.of(
                CityEntity.builder()
                        .name("Warsaw")
                        .countryName("Poland")
                        .cityLocationY(1.0)
                        .cityLocationX(2.0)
                        .cityPopulation(1700000L)
                        .destinationCitiesNumber(50L)
                        .flightsNumberFromCity(100L)
                        .flightsNumberToCity(200L)
                        .originCitiesNumber(60L)
                        .busiestMonthFlightsFromCity(1)
                        .busiestMonthFlightsToCity(2)
                        .build());

        cities = List.of(
                City.builder()
                        .name("Warsaw")
                        .countryName("Poland")
                        .cityLocationY(1.0)
                        .cityLocationX(2.0)
                        .cityPopulation(1700000L)
                        .destinationCitiesNumber(50L)
                        .flightsNumberFromCity(100L)
                        .flightsNumberToCity(200L)
                        .totalFlightsNumber(300L)
                        .originCitiesNumber(60L)
                        .busiestMonthFlightsFromCity(1)
                        .busiestMonthFlightsToCity(2)
                        .build());
    }

    @Test
    void testGetAllCities() {

        Mockito.when(cityRepository.findAll()).thenReturn(cityEntities);

        var foundCities = cityService.getAllCities();

        assertEquals(cities, foundCities);
    }

    @Test
    void testGetCityByName() {

        var cityName = "Warsaw";

        Mockito.when(cityRepository.findCityByName(cityName))
                .thenReturn(cityEntities);
        var foundCities = cityService.getCityByName(cityName);

        assertEquals(cities, foundCities);
    }
}
