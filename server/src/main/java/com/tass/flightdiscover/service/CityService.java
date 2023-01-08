package com.tass.flightdiscover.service;

import com.tass.flightdiscover.converters.CityConverter;
import com.tass.flightdiscover.domain.city.City;
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
    private final CityConverter cityConverter;

    public List<City> getAllCities() {
        return cityConverter
                .convertList(cityRepository.findAll());
    }

    public List<City> getCityByName(String name) {
        return cityConverter.convertList(cityRepository.findCityByName(name));
    }
}
