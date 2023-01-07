package com.tass.flightdiscover.controller;

import com.tass.flightdiscover.domain.City;
import com.tass.flightdiscover.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api")
public class CityController {

    private final CityService cityService;

    @GetMapping(value = "/getCities")
    public ResponseEntity<List<City>> getCities() {
        return ResponseEntity.ok(this.cityService.getCities());
    }

    @GetMapping(value = "/getPopularCities")
    public ResponseEntity<List<City>> getPopularCities(@RequestParam Double populationToFlightsRatio) {
        return ResponseEntity.ok(cityService.getPopularCities(populationToFlightsRatio));
    }
}
