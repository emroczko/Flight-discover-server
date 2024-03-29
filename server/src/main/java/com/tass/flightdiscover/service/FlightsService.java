package com.tass.flightdiscover.service;

import com.tass.flightdiscover.converters.FlightConverter;
import com.tass.flightdiscover.domain.flight.Flight;
import com.tass.flightdiscover.domain.flight.Location;
import com.tass.flightdiscover.repository.FlightsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class FlightsService {
    private final FlightsRepository flightsRepository;
    private final FlightConverter flightConverter;

    public List<Flight> getFlightsByOriginLocation(Location origin) {
        return flightConverter.convertList(flightsRepository
                .findByOriginCityAndOriginCountry(origin.getCity(), origin.getCountry()));
    }

    public List<Flight> getFlightsByDestinationLocation(Location destination) {
        return flightConverter.convertList(flightsRepository
                .findByDestinationCityAndDestinationCountry(destination.getCity(), destination.getCountry()));
    }

    public List<Flight> getFlightsByOriginAndDestinationLocation(Location origin, Location destination) {
        return flightConverter.convertList(flightsRepository.findByOriginCityAndOriginCountryAndDestinationCityAndDestinationCountry(
                origin.getCity(), origin.getCountry(), destination.getCity(), destination.getCountry()));
    }

}
