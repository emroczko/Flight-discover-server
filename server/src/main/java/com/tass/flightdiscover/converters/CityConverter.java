package com.tass.flightdiscover.converters;

import com.tass.flightdiscover.domain.city.City;
import com.tass.flightdiscover.domain.city.CityEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class CityConverter implements Converter<CityEntity, City> {
    @Override
    public City convert(CityEntity cityEntity) {
        return City.builder()
                .name(cityEntity.getName())
                .cityLocationX(cityEntity.getCityLocationX())
                .cityLocationY(cityEntity.getCityLocationY())
                .countryName(cityEntity.getCountryName())
                .cityPopulation(cityEntity.getCityPopulation())
                .flightsNumberFromCity(cityEntity.getFlightsNumberFromCity())
                .flightsNumberToCity(cityEntity.getFlightsNumberToCity())
                .totalFlightsNumber(cityEntity.getFlightsNumberToCity() + cityEntity.getFlightsNumberFromCity())
                .originCitiesNumber(cityEntity.getOriginCitiesNumber())
                .destinationCitiesNumber(cityEntity.getDestinationCitiesNumber())
                .totalConnectedCitiesNumber(cityEntity.getTotalConnectedCitiesNumber())
                .originCountriesNumber(cityEntity.getOriginCountriesNumber())
                .destinationCountriesNumber(cityEntity.getDestinationCountriesNumber())
                .busiestMonthFlightsFromCity(cityEntity.getBusiestMonthFlightsFromCity())
                .busiestMonthFlightsToCity(cityEntity.getBusiestMonthFlightsToCity())
                .flightsNumberFromCityToPopulationRatio(cityEntity.getFlightsNumberFromCityToPopulationRatio())
                .flightsNumberToCityToPopulationRatio(cityEntity.getFlightsNumberToCityToPopulationRatio())
                .totalFlightsNumberForCityToPopulationRatio(cityEntity.getTotalFlightsNumberForCityToPopulationRatio())
                .build();
    }

    public List<City> convertList(List<CityEntity> cities) {
        return cities
                .stream()
                .flatMap(cityEntity -> {
                    var cityResponse = convert(cityEntity);
                    return cityResponse != null ? Stream.of(cityResponse) : Stream.empty();
                })
                .toList();
    }
}
