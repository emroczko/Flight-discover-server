package com.tass.flightdiscover.converter;

import com.tass.flightdiscover.converters.CityConverter;
import com.tass.flightdiscover.domain.city.City;
import com.tass.flightdiscover.domain.city.CityEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CityConverterTests {

    private static List<CityEntity> cityEntities;
    private static List<City> cities;
    @Autowired
    private CityConverter cityConverter;

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
    void testCityConverter() {
        var converted = cityConverter.convert(cityEntities.get(0));
        assertEquals(cities.get(0), converted);
    }

    @Test
    void testCitiesListConverter() {
        var converted = cityConverter.convertList(cityEntities);
        assertEquals(cities, converted);
    }

}
