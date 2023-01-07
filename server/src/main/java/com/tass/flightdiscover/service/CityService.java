package com.tass.flightdiscover.service;

import com.tass.flightdiscover.domain.City;
import com.tass.flightdiscover.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<City> getCities() {
        return this.cityRepository.findAll();
    }

    public List<City> getPopularCities(Double ratio) {
        return cityRepository.findByTotalFlightsNumberForCityToPopulationRatioGreaterThanEqual(ratio);
    }
}
