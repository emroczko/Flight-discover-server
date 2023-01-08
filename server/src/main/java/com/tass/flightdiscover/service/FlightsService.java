package com.tass.flightdiscover.service;

import com.tass.flightdiscover.converters.FlightConversionService;
import com.tass.flightdiscover.domain.flight.Flight;
import com.tass.flightdiscover.domain.flight.FlightResponse;
import com.tass.flightdiscover.domain.flight.Location;
import com.tass.flightdiscover.exceptions.CityNotFoundException;
import com.tass.flightdiscover.repository.FlightsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
@AllArgsConstructor
public class FlightsService {
    private final FlightsRepository flightsRepository;
    private final FlightConversionService flightConversionService;

    public List<FlightResponse> getFlightsByOriginLocation(Location origin) {
        return flightConversionService.convertList(flightsRepository
                .findByOriginCityAndOriginCountry(origin.getCity(), origin.getCountry()));
    }

    public List<FlightResponse> getFlightsByDestinationLocation(Location destination) {
        return flightConversionService.convertList(flightsRepository
                .findByDestinationCityAndDestinationCountry(destination.getCity(), destination.getCountry()));
    }

    public FlightResponse getFlightsByOriginAndDestinationLocation(Location origin, Location destination) throws CityNotFoundException {
        return flightsRepository.findByOriginCityAndOriginCountryAndDestinationCityAndDestinationCountry(
                        origin.getCity(), origin.getCountry(), destination.getCity(), destination.getCountry())
                .map(flightConversionService::convert)
                .orElseThrow(() -> {
                    var message = "Flight from %s, %s to %s, %s not found!".formatted(origin.getCity(), origin.getCountry(), destination.getCity(), destination.getCountry());
                    log.info(message);
                    return new CityNotFoundException(message);
    });
    }

}
