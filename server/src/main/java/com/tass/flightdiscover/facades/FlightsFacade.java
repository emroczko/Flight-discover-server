package com.tass.flightdiscover.facades;

import com.tass.flightdiscover.domain.flight.ConnectionRequest;
import com.tass.flightdiscover.domain.flight.FlightRequest;
import com.tass.flightdiscover.domain.flight.Flight;
import com.tass.flightdiscover.exceptions.BadRequestException;
import com.tass.flightdiscover.exceptions.FlightNotFoundException;
import com.tass.flightdiscover.service.FlightsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FlightsFacade {
    private final FlightsService flightsService;

    public List<Flight> getFlights(FlightRequest request) throws BadRequestException, FlightNotFoundException {

        var reversed = request.getReverse() != null ? request.getReverse() : false;
        var destination = reversed ? request.getFrom() : request.getTo();
        var source = reversed ? request.getTo() : request.getFrom();

        if (source != null && destination != null) {
            return throwIfEmpty(flightsService.getFlightsByOriginAndDestinationLocation(source, destination));
        } else if (destination != null) {
            return throwIfEmpty(flightsService.getFlightsByDestinationLocation(destination));
        } else if (source != null) {
            return throwIfEmpty(flightsService.getFlightsByOriginLocation(source));
        }
        throw new BadRequestException("Wrong set of parameters");
    }

    public List<Flight> getAllConnections(ConnectionRequest connectionRequest) throws FlightNotFoundException {
        var firstLocation = connectionRequest.getFirstLocation();
        var secondLocation = connectionRequest.getSecondLocation();

        return throwIfEmpty(
                Stream.concat(
                        flightsService.getFlightsByOriginAndDestinationLocation(firstLocation, secondLocation).stream(),
                        flightsService.getFlightsByOriginAndDestinationLocation(secondLocation, firstLocation).stream()
                ).toList());
    }

    private <T> List<T> throwIfEmpty(List<T> list) throws FlightNotFoundException {
        if (list.isEmpty()) {
            throw new FlightNotFoundException("No flights found for given locations!");
        }
        return list;
    }
}
