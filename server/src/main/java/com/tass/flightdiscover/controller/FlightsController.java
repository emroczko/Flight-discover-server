package com.tass.flightdiscover.controller;

import com.tass.flightdiscover.FlightsFacade;
import com.tass.flightdiscover.domain.flight.FlightRequest;
import com.tass.flightdiscover.domain.flight.FlightResponse;
import com.tass.flightdiscover.exceptions.CityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(value = "/flights")
public class FlightsController {

    private final FlightsFacade flightsFacade;

    @GetMapping(value = "/getByLocations")
    public ResponseEntity<List<FlightResponse>> findFlight(FlightRequest request) throws CityNotFoundException {
        log.info("Find flight request {}", request);
        return ResponseEntity.ok(flightsFacade.getFlights(request));
    }
}
