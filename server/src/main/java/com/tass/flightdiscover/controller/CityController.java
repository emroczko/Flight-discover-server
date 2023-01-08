package com.tass.flightdiscover.controller;

import com.tass.flightdiscover.domain.city.City;
import com.tass.flightdiscover.domain.city.CityRequest;
import com.tass.flightdiscover.exceptions.CityNotFoundException;
import com.tass.flightdiscover.facades.CityFacade;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(value = "/cities")
public class CityController {

    private final CityFacade cityFacade;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<City>> get(@ParameterObject CityRequest request) throws CityNotFoundException {
        log.info("City request: {}", request);
        return ResponseEntity.ok(cityFacade.getCities(request));
    }

    @RequestMapping(value = "/getByName", method = RequestMethod.GET)
    public ResponseEntity<List<City>> get(@RequestParam @NotBlank(message = "Name field is required") String name) throws CityNotFoundException {
        log.info("Searching for city: {}", name);
        return ResponseEntity.ok(cityFacade.getCitiesByName(name));
    }
}
