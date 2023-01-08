package com.tass.flightdiscover.facades;

import com.tass.flightdiscover.domain.flight.ConnectionRequest;
import com.tass.flightdiscover.domain.flight.FlightRequest;
import com.tass.flightdiscover.domain.flight.FlightResponse;
import com.tass.flightdiscover.exceptions.FlightNotFoundException;
import com.tass.flightdiscover.service.FlightsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FlightsFacade {
    private final FlightsService flightsService;

    public List<FlightResponse> getFlights(FlightRequest request) throws FlightNotFoundException {

        var reversed = request.getReverse() != null ? request.getReverse() : false;
        var destination = reversed ? request.getFrom() : request.getTo();
        var source = reversed ? request.getTo() : request.getFrom();

        if (source == null && destination != null) {
            return flightsService.getFlightsByDestinationLocation(request.getTo());
        } else if (destination == null && source != null) {
            return flightsService.getFlightsByOriginLocation(request.getFrom());
        } else if (source != null) {
            return flightsService.getFlightsByOriginAndDestinationLocation(source, destination);
        }
        return Collections.emptyList();
    }

    public List<FlightResponse> getAllConnections(ConnectionRequest connectionRequest) throws FlightNotFoundException {
        var firstLocation = connectionRequest.getFirstLocation();
        var secondLocation = connectionRequest.getSecondLocation();

        return Stream.concat(
                flightsService.getFlightsByOriginAndDestinationLocation(firstLocation, secondLocation).stream(),
                flightsService.getFlightsByOriginAndDestinationLocation(secondLocation, firstLocation).stream()
        ).toList();
    }
}
