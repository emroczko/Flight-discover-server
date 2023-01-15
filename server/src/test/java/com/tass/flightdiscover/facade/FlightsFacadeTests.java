package com.tass.flightdiscover.facade;

import com.tass.flightdiscover.domain.flight.ConnectionRequest;
import com.tass.flightdiscover.domain.flight.FlightRequest;
import com.tass.flightdiscover.domain.flight.Location;
import com.tass.flightdiscover.exceptions.BadRequestException;
import com.tass.flightdiscover.exceptions.CityNotFoundException;
import com.tass.flightdiscover.exceptions.FlightNotFoundException;
import com.tass.flightdiscover.facades.FlightsFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FlightsFacadeTests {

    @Autowired
    private FlightsFacade flightsFacade;

    @Test
    void testGetFlights() throws FlightNotFoundException, BadRequestException {

        var originCity = "Aberdeen";
        var originCountry = "United States";
        var destinationCity = "Minneapolis";
        var destinationCountry = "United States";

        var flightRequest = FlightRequest.builder()
                .from(Location.builder().city(originCity).country(originCountry).build())
                .to(Location.builder().city(destinationCity).country(destinationCountry).build())
                .build();

        var foundFlights = flightsFacade.getFlights(flightRequest);

        assertEquals(1, foundFlights.size());

        var foundFlight = foundFlights.get(0);
        assertAll(() -> {
            assertEquals(671, foundFlight.getNumberOfFlights());
            assertEquals(originCity, foundFlight.getFrom().getCity());
            assertEquals(originCountry, foundFlight.getFrom().getCountry());
            assertEquals(destinationCity, foundFlight.getTo().getCity());
            assertEquals(destinationCountry, foundFlight.getTo().getCountry());
        });
    }

    @Test
    void testGetFlightsReversed() throws FlightNotFoundException, BadRequestException {

        var originCity = "Aberdeen";
        var originCountry = "United States";
        var destinationCity = "Minneapolis";
        var destinationCountry = "United States";

        var flightRequest = FlightRequest.builder()
                .from(Location.builder().city(originCity).country(originCountry).build())
                .to(Location.builder().city(destinationCity).country(destinationCountry).build())
                .reverse(true)
                .build();

        var foundFlights = flightsFacade.getFlights(flightRequest);

        assertEquals(1, foundFlights.size());

        var foundFlight = foundFlights.get(0);
        assertAll(() -> {
            assertEquals(1340, foundFlight.getNumberOfFlights());
            assertEquals(originCity, foundFlight.getTo().getCity());
            assertEquals(originCountry, foundFlight.getTo().getCountry());
            assertEquals(destinationCity, foundFlight.getFrom().getCity());
            assertEquals(destinationCountry, foundFlight.getFrom().getCountry());
        });
    }

    @Test
    void testGetFlightsNotFound() {
        var flightRequest = FlightRequest.builder()
                .from(Location.builder().city("Warsaw").country("Poland").build())
                .build();

        var exception = assertThrows(FlightNotFoundException.class,
                () ->  flightsFacade.getFlights(flightRequest));

        var expectedMessage = "No flights found for given locations!";
        var actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetAllConnections() throws FlightNotFoundException {

        var firstCity = "Aberdeen";
        var firstCountry = "United States";
        var secondCity = "Minneapolis";
        var secondCountry = "United States";

        var connectionRequest = ConnectionRequest.builder()
                .firstLocation(Location.builder().city(firstCity).country(firstCountry).build())
                .secondLocation(Location.builder().city(secondCity).country(secondCountry).build())
                .build();

        var foundConnections = flightsFacade.getAllConnections(connectionRequest);

        assertEquals(2, foundConnections.size());

        var firstFoundConnection = foundConnections.get(0);

        assertAll(() -> {
            assertEquals(671, firstFoundConnection.getNumberOfFlights());
            assertEquals(firstCity, firstFoundConnection.getFrom().getCity());
            assertEquals(firstCountry, firstFoundConnection.getFrom().getCountry());
            assertEquals(secondCity, firstFoundConnection.getTo().getCity());
            assertEquals(secondCountry, firstFoundConnection.getTo().getCountry());
        });

        var secondFoundConnection = foundConnections.get(1);

        assertAll(() -> {
            assertEquals(1340, secondFoundConnection.getNumberOfFlights());
            assertEquals(secondCity, secondFoundConnection.getFrom().getCity());
            assertEquals(secondCountry, secondFoundConnection.getFrom().getCountry());
            assertEquals(firstCity, secondFoundConnection.getTo().getCity());
            assertEquals(firstCountry, secondFoundConnection.getTo().getCountry());
        });
    }

    @Test
    void testGetAllConnectionsNotFound() {
        var connectionRequest = ConnectionRequest.builder()
                .firstLocation(Location.builder().city("Warsaw").country("Poland").build())
                .secondLocation(Location.builder().city("Krakow").country("Poland").build())
                .build();

        var exception = assertThrows(FlightNotFoundException.class,
                () ->  flightsFacade.getAllConnections(connectionRequest));

        var expectedMessage = "No flights found for given locations!";
        var actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
