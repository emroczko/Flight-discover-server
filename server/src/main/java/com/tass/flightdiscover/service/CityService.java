package com.tass.flightdiscover.service;

import com.tass.flightdiscover.converters.CityConversionService;
import com.tass.flightdiscover.domain.City;
import com.tass.flightdiscover.domain.CityResponse;
import com.tass.flightdiscover.exceptions.CityNotFoundException;
import com.tass.flightdiscover.repository.CityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CityConversionService cityConversionService;

    public List<CityResponse> getCities() {
        return cityRepository
                .findAll()
                .stream()
                .flatMap(city -> {
                    var cityResponse = cityConversionService.convert(city);
                    return cityResponse != null ? Stream.of(cityResponse) : Stream.empty();
                })
                .toList();
    }

    public CityResponse getCity(String name) throws CityNotFoundException {
        return cityRepository
                .findCityByName(name)
                .map(cityConversionService::convert)
                .orElseThrow(() -> {
                    var message = "City %s not found!".formatted(name);
                    log.info("City {} not found", name);
                    return new CityNotFoundException(message);
                });
    }

    public List<CityResponse> getPopularCities(Double ratio) {
        return cityRepository.findByTotalFlightsNumberForCityToPopulationRatioGreaterThanEqual(ratio)
                .stream()
                .flatMap(city -> {
                    var cityResponse = cityConversionService.convert(city);
                    return cityResponse != null ? Stream.of(cityResponse) : Stream.empty();
                })
                .toList();
    }
}
