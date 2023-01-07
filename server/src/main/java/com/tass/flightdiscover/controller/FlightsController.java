package com.tass.flightdiscover.controller;

import com.tass.flightdiscover.domain.flight.FlightRequest;
import com.tass.flightdiscover.domain.flight.FlightResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(value = "/flights")
public class FlightsController {
    @GetMapping(value = "/getByLocations")
    public ResponseEntity<List<FlightResponse>> findFlight(@RequestParam FlightRequest request) {
        log.info("Find flight request {}", request);
        return ResponseEntity.ok(null);
    }
}
