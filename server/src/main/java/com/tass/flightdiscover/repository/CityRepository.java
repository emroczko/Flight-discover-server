package com.tass.flightdiscover.repository;

import com.tass.flightdiscover.domain.city.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {
    List<CityEntity> findCityByName(String name);
}
