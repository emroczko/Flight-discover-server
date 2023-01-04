package com.tass.flightdiscover.controller;

import com.tass.flightdiscover.domain.City;
import com.tass.flightdiscover.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<City>> getCities() {
        return ResponseEntity.ok(this.cityService.getCities());
    }
}
