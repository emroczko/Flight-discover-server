package com.tass.flightdiscover.service;

import com.tass.flightdiscover.domain.City;
import com.tass.flightdiscover.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getCities() {
        return this.cityRepository.findAll();
    }
}
