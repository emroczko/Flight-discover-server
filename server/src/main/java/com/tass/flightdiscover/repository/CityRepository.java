package com.tass.flightdiscover.repository;

import com.tass.flightdiscover.domain.city.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {
    List<CityEntity> findCityByName(String name);

    List<CityEntity> findByTotalFlightsNumberForCityToPopulationRatioGreaterThanEqual(Double totalFlightsNumberForCityToPopulationRatio);
//    private Long minPopulation;
//    private Long maxPopulation;
//    private Long minFlightsNumberFromCity;
//    private Long maxFlightsNumberFromCity;
//    private Long minFlightsNumberToCity;
//    private Long maxFlightsNumberToCity;
//    private Long minSrcCitiesNumber;
//    private Long maxSrcCitiesNumber;
//    private Long minDestCitiesNumber;
//    private Long maxDestCitiesNumber;
//    private Long minTotalConnectedCitiesNumber;
//    private Long maxTotalConnectedCitiesNumber;
//    private Long minTotalFlightsToPopulationRatio;
//    private Long maxTotalFlightsToPopulationRatio;
}
