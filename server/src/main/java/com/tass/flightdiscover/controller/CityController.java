package com.tass.flightdiscover.controller;

import com.tass.flightdiscover.configuration.swagger.CitiesSwaggerConfiguration;
import com.tass.flightdiscover.domain.city.City;
import com.tass.flightdiscover.domain.city.CityRequest;
import com.tass.flightdiscover.exceptions.CityNotFoundException;
import com.tass.flightdiscover.facades.CityFacade;
import io.swagger.v3.oas.annotations.Operation;
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

    @CitiesSwaggerConfiguration
    @Operation(description = """
            Returns cities by multiple conditions given in request. Cities can be also sorted by multiple conditions.
            If no parameters passed it will return all cities.
            """)
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<City>> get(@ParameterObject CityRequest request) throws CityNotFoundException {
        log.info("City request: {}", request);
        return ResponseEntity.ok(cityFacade.getCities(request));
    }

    @CitiesSwaggerConfiguration
    @Operation(description = "Returns cities of name given in parameter. Cities can be also sorted by multiple conditions")
    @RequestMapping(value = "/getByName", method = RequestMethod.GET)
    public ResponseEntity<List<City>> get(@RequestParam @NotBlank(message = "Name field is required") String name) throws CityNotFoundException {
        log.info("Searching for city: {}", name);
        return ResponseEntity.ok(cityFacade.getCitiesByName(name));
    }
}
