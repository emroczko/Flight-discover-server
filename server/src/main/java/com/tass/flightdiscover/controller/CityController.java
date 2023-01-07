package com.tass.flightdiscover.controller;

import com.tass.flightdiscover.domain.City;
import com.tass.flightdiscover.domain.CityResponse;
import com.tass.flightdiscover.domain.FlightResponse;
import com.tass.flightdiscover.exceptions.CityNotFoundException;
import com.tass.flightdiscover.service.CityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api")
public class CityController {

    private final CityService cityService;

    @GetMapping(value = "/getAllCities")
    public ResponseEntity<List<CityResponse>> getAllCities() {
        return ResponseEntity.ok(this.cityService.getCities());
    }

    @GetMapping(value = "/getCity")
    public ResponseEntity<CityResponse> getCity(String name) throws CityNotFoundException {
        log.info("Searching for city: {}", name);
        return ResponseEntity.ok(cityService.getCity(name));
    }

    @GetMapping(value = "/getPopularCities")
    public ResponseEntity<List<CityResponse>> getPopularCities(@RequestParam Double populationToFlightsRatio) {
        return ResponseEntity.ok(cityService.getPopularCities(populationToFlightsRatio));
    }

    @GetMapping(value = "/findFlight")
    public ResponseEntity<List<FlightResponse>> findFlight(@RequestParam String from, @RequestParam String to) {
        return ResponseEntity.ok(null);
    }
}
