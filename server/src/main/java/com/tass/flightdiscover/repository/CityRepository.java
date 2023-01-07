package com.tass.flightdiscover.repository;

import com.tass.flightdiscover.domain.city.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findCityByName(String name);

    List<City> findByTotalFlightsNumberForCityToPopulationRatioGreaterThanEqual(Double totalFlightsNumberForCityToPopulationRatiow);
}
