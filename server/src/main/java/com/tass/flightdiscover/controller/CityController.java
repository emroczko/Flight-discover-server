package com.tass.flightdiscover.controller;

import com.tass.flightdiscover.domain.city.CityResponse;
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
@RequestMapping(value = "/cities")
public class CityController {

    private final CityService cityService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<CityResponse>> getAllCities() {
        return ResponseEntity.ok(this.cityService.getCities());
    }

    @RequestMapping(value = "/getByName", method = RequestMethod.GET)
    public ResponseEntity<CityResponse> getCity(String name) throws CityNotFoundException {
        log.info("Searching for city: {}", name);
        return ResponseEntity.ok(cityService.getCity(name));
    }

    @GetMapping(value = "/getPopularByRatio")
    public ResponseEntity<List<CityResponse>> getPopularCities(@RequestParam Double populationToFlightsRatio) {
        return ResponseEntity.ok(cityService.getPopularCities(populationToFlightsRatio));
    }
}
