package com.tass.flightdiscover.service;

import com.tass.flightdiscover.domain.flight.Flight;
import com.tass.flightdiscover.domain.flight.FlightsEntity;
import com.tass.flightdiscover.domain.flight.Location;
import com.tass.flightdiscover.repository.FlightsRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class FlightsServiceTests {

    private static List<FlightsEntity> flightsEntities;
    @Autowired
    private FlightsService flightsService;
    @MockBean
    private FlightsRepository flightsRepository;

    @BeforeAll
    static void prepareData() {
        flightsEntities = List.of(
                FlightsEntity.builder()
                        .originCity("Warsaw")
                        .originCountry("Poland")
                        .destinationCity("Berlin")
                        .destinationCountry("Germany")
                        .count(100L)
                        .build()
        );
    }


    @Test
    void testGetFlightsByOriginLocation() {
        var originCity = "Warsaw";
        var originCountry = "Poland";
        Mockito.when(flightsRepository.findByOriginCityAndOriginCountry(originCity, originCountry))
                .thenReturn(flightsEntities);

        var foundFlight = flightsService.getFlightsByOriginLocation(Location.builder()
                .city(originCity)
                .country(originCountry)
                .build());

        assertEquals(List.of(
                Flight.builder()
                        .to(Location.builder()
                                .city("Berlin")
                                .country("Germany")
                                .build())
                        .from(Location.builder()
                                .city("Warsaw")
                                .country("Poland")
                                .build())
                        .numberOfFlights(100L)
                        .build()
        ), foundFlight);
    }

    @Test
    void getFlightsByDestinationLocation() {
        var destinationCity = "Berlin";
        var destinationCountry = "Germany";
        Mockito.when(flightsRepository.findByDestinationCityAndDestinationCountry(destinationCity, destinationCountry))
                .thenReturn(flightsEntities);

        var foundFlight = flightsService.getFlightsByDestinationLocation(Location.builder()
                .city(destinationCity)
                .country(destinationCountry)
                .build());

        assertEquals(List.of(
                Flight.builder()
                        .to(Location.builder()
                                .city("Berlin")
                                .country("Germany")
                                .build())
                        .from(Location.builder()
                                .city("Warsaw")
                                .country("Poland")
                                .build())
                        .numberOfFlights(100L)
                        .build()
        ), foundFlight);
    }

    @Test
    void getFlightsByOriginAndDestinationLocation() {

        var originCity = "Warsaw";
        var originCountry = "Poland";
        var destinationCity = "Berlin";
        var destinationCountry = "Germany";

        Mockito.when(flightsRepository.findByOriginCityAndOriginCountryAndDestinationCityAndDestinationCountry(
                        originCity, originCountry, destinationCity, destinationCountry))
                .thenReturn(flightsEntities);

        var foundFlight = flightsService.getFlightsByOriginAndDestinationLocation(
                Location.builder()
                        .city(originCity)
                        .country(originCountry)
                        .build(),
                Location.builder()
                        .city(destinationCity)
                        .country(destinationCountry)
                        .build());

        var expectedFlight = List.of(
                Flight.builder()
                        .from(Location.builder()
                                .city("Warsaw")
                                .country("Poland")
                                .build())
                        .to(Location.builder()
                                .city("Berlin")
                                .country("Germany")
                                .build())
                        .numberOfFlights(100L)
                        .build()
        );


        assertEquals(expectedFlight, foundFlight);
    }
}
