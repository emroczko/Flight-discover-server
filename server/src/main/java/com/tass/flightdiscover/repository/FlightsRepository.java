package com.tass.flightdiscover.repository;

import com.tass.flightdiscover.domain.flight.FlightsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightsRepository extends JpaRepository<FlightsEntity, Long> {
    List<FlightsEntity> findByOriginCityAndOriginCountry(String originCity, String originCountry);

    List<FlightsEntity> findByDestinationCityAndDestinationCountry(String destinationCity, String destinationCountry);

    List<FlightsEntity> findByOriginCityAndOriginCountryAndDestinationCityAndDestinationCountry(String originCity,
                                                                                                String originCountry,
                                                                                                String destinationCity,
                                                                                                String destinationCountry);
}
