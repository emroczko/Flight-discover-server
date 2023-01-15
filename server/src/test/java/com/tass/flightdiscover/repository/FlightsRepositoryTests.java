package com.tass.flightdiscover.repository;

import com.tass.flightdiscover.domain.flight.FlightsEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class FlightsRepositoryTests {

    @Autowired
    private FlightsRepository flightsRepository;

    private static List<FlightsEntity> expectedFlights;

    @BeforeAll
    static void prepareData() {
        expectedFlights = List.of(FlightsEntity.builder()
                .id(1L)
                .originCity("Aberdeen")
                .originCountry("United States")
                .destinationCity("Minneapolis")
                .destinationCountry("United States")
                .count(671L)
                .build()
        );
    }

    @Test
    void testFindByOriginCityAndOriginCountry() {
        var originCity = "Aberdeen";
        var originCountry = "United States";

        var foundFlights = flightsRepository.findByOriginCityAndOriginCountry(originCity, originCountry);

        assertEquals(1, foundFlights.size());
        assertEquals(expectedFlights, foundFlights);
    }

    @Test
    void testFindByDestinationCityAndDestinationCountry() {
        var destinationCity = "Minneapolis";
        var destinationCountry = "United States";

        var foundFlights = flightsRepository
                .findByDestinationCityAndDestinationCountry(destinationCity, destinationCountry);

        assertEquals(1, foundFlights.size());
        assertEquals(expectedFlights, foundFlights);
    }

    @Test
    void testFindByOriginCityAndOriginCountryAndDestinationCityAndDestinationCountry() {

        var originCity = "Aberdeen";
        var originCountry = "United States";
        var destinationCity = "Minneapolis";
        var destinationCountry = "United States";

        var foundFlights = flightsRepository
                .findByOriginCityAndOriginCountryAndDestinationCityAndDestinationCountry(
                        originCity,
                        originCountry,
                        destinationCity,
                        destinationCountry);

        assertEquals(1, foundFlights.size());
        assertEquals(expectedFlights, foundFlights);
    }
}
