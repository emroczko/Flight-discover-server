package com.tass.flightdiscover.repository;

import com.tass.flightdiscover.domain.flight.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightsRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByOriginCityAndOriginCountry(String originCity, String originCountry);

    List<Flight> findByDestinationCityAndDestinationCountry(String destinationCity, String destinationCountry);

    Optional<Flight> findByOriginCityAndOriginCountryAndDestinationCityAndDestinationCountry(String originCity,
                                                                                             String originCountry,
                                                                                             String destinationCity,
                                                                                             String destinationCountry);
}
