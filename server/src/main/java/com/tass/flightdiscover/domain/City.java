package com.tass.flightdiscover.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "cities")
@Getter
@Setter
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "city_location_y")
    private Double cityLocationY;

    @Column(name = "city_location_x")
    private Double cityLocationX;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "city_population")
    private Double cityPopulation;

    @Column(name = "flights_number_from_city")
    private Long flightsNumberFromCity;

    @Column(name = "flights_number_to_city")
    private Long flightsNumberToCity;

    @Column(name = "origin_cities_number")
    private Long originCitiesNumber;

    @Column(name = "destination_cities_number")
    private Long destinationCitiesNumber;

    @Column(name = "total_connected_cities_number")
    private Long totalConnectedCitiesNumber;

    @Column(name = "origin_countries_number")
    private Long originCountriesNumber;

    @Column(name = "destination_countries_number")
    private Long destinationCountriesNumber;

    @Column(name = "busiest_month_flights_from_city")
    private Integer busiestMonthFlightsFromCity;

    @Column(name = "busiest_month_flights_to_city")
    private Integer busiestMonthFlightsToCity;

    @Column(name = "flights_number_from_city_to_population_ratio")
    private Double flightsNumberFromCityToPopulationRatio;

    @Column(name = "flights_number_to_city_to_population_ratio")
    private Double flightsNumberToCityToPopulationRatio;

    @Column(name = "total_flights_number_for_city_to_population_ratio")
    private Double totalFlightsNumberForCityToPopulationRatio;
}