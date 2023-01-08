package com.tass.flightdiscover.controller;

import com.tass.flightdiscover.domain.city.CityRequest;
import com.tass.flightdiscover.domain.city.CityResponse;
import com.tass.flightdiscover.service.CityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(value = "/cities")
public class CityController {

    private final CityService cityService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<CityResponse>> getAllCities(CityRequest request) {
        log.info("City request: {}", request);
        return ResponseEntity.ok(this.cityService.getCities());
    }
}
