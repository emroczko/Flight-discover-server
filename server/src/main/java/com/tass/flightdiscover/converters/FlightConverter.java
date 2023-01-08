package com.tass.flightdiscover.converters;

import com.tass.flightdiscover.domain.flight.FlightsEntity;
import com.tass.flightdiscover.domain.flight.Flight;
import com.tass.flightdiscover.domain.flight.Location;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class FlightConverter implements Converter<FlightsEntity, Flight> {

    @Override
    public Flight convert(FlightsEntity flightsEntity) {
        return Flight.builder()
                .from(Location.builder()
                        .city(flightsEntity.getOriginCity())
                        .country(flightsEntity.getOriginCountry())
                        .build())
                .to(Location.builder()
                        .city(flightsEntity.getDestinationCity())
                        .country(flightsEntity.getDestinationCountry())
                        .build())
                .numberOfFlights(flightsEntity.getCount())
                .build();
    }

    public List<Flight> convertList(List<FlightsEntity> flightEntities) {
        return flightEntities
                .stream()
                .flatMap(flightsEntity -> {
                    var flightResponse = convert(flightsEntity);
                    return flightResponse != null ? Stream.of(flightResponse) : Stream.empty();
                })
                .toList();
    }
}
