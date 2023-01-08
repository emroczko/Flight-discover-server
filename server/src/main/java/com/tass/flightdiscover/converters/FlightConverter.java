package com.tass.flightdiscover.converters;

import com.tass.flightdiscover.domain.Location;
import com.tass.flightdiscover.domain.flight.Flight;
import com.tass.flightdiscover.domain.flight.FlightResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class FlightConverter implements Converter<Flight, FlightResponse> {

    @Override
    public FlightResponse convert(Flight flight) {
        return FlightResponse.builder()
                .from(Location.builder()
                        .city(flight.getOriginCity())
                        .country(flight.getOriginCountry())
                        .build())
                .to(Location.builder()
                        .city(flight.getDestinationCity())
                        .country(flight.getDestinationCountry())
                        .build())
                .numberOfFlights(flight.getCount())
                .build();
    }

    public List<FlightResponse> convertList(List<Flight> flights) {
        return flights
                .stream()
                .flatMap(flight -> {
                    var flightResponse = convert(flight);
                    return flightResponse != null ? Stream.of(flightResponse) : Stream.empty();
                })
                .toList();
    }
}
