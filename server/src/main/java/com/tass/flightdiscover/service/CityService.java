package com.tass.flightdiscover.service;

import com.tass.flightdiscover.domain.City;
import com.tass.flightdiscover.exceptions.CityNotFoundException;
import com.tass.flightdiscover.repository.CityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<City> getCities() {
        return this.cityRepository.findAll();
    }

    public City getCity(String name) throws CityNotFoundException {
        return cityRepository
                .findCityByName(name)
                .orElseThrow(() -> {
                    var message = "City %s not found!".formatted(name);
                    log.info("City {} not found", name);
                    return new CityNotFoundException(message);
                });
    }

    public List<City> getPopularCities(Double ratio) {
        return cityRepository.findByTotalFlightsNumberForCityToPopulationRatioGreaterThanEqual(ratio);
    }
}
