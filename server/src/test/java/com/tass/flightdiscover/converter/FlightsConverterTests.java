package com.tass.flightdiscover.converter;

import com.tass.flightdiscover.converters.FlightConverter;
import com.tass.flightdiscover.domain.flight.Flight;
import com.tass.flightdiscover.domain.flight.FlightsEntity;
import com.tass.flightdiscover.domain.flight.Location;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FlightsConverterTests {

    private static List<FlightsEntity> flightsEntities;
    private static List<Flight> flights;

    @Autowired
    private FlightConverter flightConverter;

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

        flights = List.of(
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
        );
    }

    @Test
    void testConvert() {
        var converted = flightConverter.convert(flightsEntities.get(0));
        assertEquals(flights.get(0), converted);
    }

    @Test
    void testListConvert() {
        var converted = flightConverter.convertList(flightsEntities);
        assertEquals(flights, converted);
    }
}
