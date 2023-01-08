package com.tass.flightdiscover;

import com.tass.flightdiscover.domain.flight.FlightRequest;
import com.tass.flightdiscover.domain.flight.FlightResponse;
import com.tass.flightdiscover.exceptions.CityNotFoundException;
import com.tass.flightdiscover.service.FlightsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class FlightsFacade {
    private final FlightsService flightsService;

    public List<FlightResponse> getFlights(FlightRequest request) throws CityNotFoundException {

        if (request.getFrom() == null && request.getTo() != null) {
            return flightsService.getFlightsByDestinationLocation(request.getTo());
        }
        else if (request.getTo() == null && request.getFrom() != null) {
            return flightsService.getFlightsByOriginLocation(request.getFrom());
        }
        else if (request.getFrom() != null && request.getTo() != null) {
            return List.of(flightsService.getFlightsByOriginAndDestinationLocation(request.getFrom(), request.getTo()));
        }
        return Collections.emptyList();
    }
}
