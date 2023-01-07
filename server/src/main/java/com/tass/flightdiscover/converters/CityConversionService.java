package com.tass.flightdiscover.converters;

import com.tass.flightdiscover.domain.City;
import com.tass.flightdiscover.domain.CityResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class CityConversionService implements Converter<City, CityResponse> {
    @Override
    public CityResponse convert(City city) {
        return CityResponse.builder()
                .name(city.getName())
                .cityLocationX(city.getCityLocationX())
                .cityLocationY(city.getCityLocationY())
                .countryName(city.getCountryName())
                .cityPopulation(city.getCityPopulation())
                .flightsNumberFromCity(city.getFlightsNumberFromCity())
                .flightsNumberToCity(city.getFlightsNumberToCity())
                .originCitiesNumber(city.getOriginCitiesNumber())
                .destinationCitiesNumber(city.getDestinationCitiesNumber())
                .totalConnectedCitiesNumber(city.getTotalConnectedCitiesNumber())
                .originCountriesNumber(city.getOriginCountriesNumber())
                .destinationCountriesNumber(city.getDestinationCountriesNumber())
                .busiestMonthFlightsFromCity(city.getBusiestMonthFlightsFromCity())
                .busiestMonthFlightsToCity(city.getBusiestMonthFlightsToCity())
                .flightsNumberFromCityToPopulationRatio(city.getFlightsNumberFromCityToPopulationRatio())
                .flightsNumberToCityToPopulationRatio(city.getFlightsNumberToCityToPopulationRatio())
                .totalFlightsNumberForCityToPopulationRatio(city.getTotalFlightsNumberForCityToPopulationRatio())
                .build();
    }
}
