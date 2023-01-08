package com.tass.flightdiscover.service;

import com.tass.flightdiscover.converters.CityConverter;
import com.tass.flightdiscover.domain.city.CityResponse;
import com.tass.flightdiscover.exceptions.CityNotFoundException;
import com.tass.flightdiscover.repository.CityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CityConverter cityConverter;

    public List<CityResponse> getCities() {
        return cityRepository
                .findAll()
                .stream()
                .flatMap(city -> {
                    var cityResponse = cityConverter.convert(city);
                    return cityResponse != null ? Stream.of(cityResponse) : Stream.empty();
                })
                .toList();
    }

    public CityResponse getCityByNameAndCountry(String name, String country) throws CityNotFoundException {
        return cityRepository
                .findCityByName(name)
                .map(cityConverter::convert)
                .orElseThrow(() -> {
                    var message = "City %s not found!".formatted(name);
                    log.info(message);
                    return new CityNotFoundException(message);
                });
    }

    public List<CityResponse> getPopularCities(Double ratio) {
        return cityRepository.findByTotalFlightsNumberForCityToPopulationRatioGreaterThanEqual(ratio)
                .stream()
                .flatMap(city -> {
                    var cityResponse = cityConverter.convert(city);
                    return cityResponse != null ? Stream.of(cityResponse) : Stream.empty();
                })
                .toList();
    }
}
