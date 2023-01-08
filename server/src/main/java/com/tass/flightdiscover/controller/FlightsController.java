package com.tass.flightdiscover.controller;

import com.tass.flightdiscover.domain.flight.ConnectionRequest;
import com.tass.flightdiscover.domain.flight.FlightRequest;
import com.tass.flightdiscover.domain.flight.FlightResponse;
import com.tass.flightdiscover.exceptions.FlightNotFoundException;
import com.tass.flightdiscover.facades.FlightsFacade;
import com.tass.flightdiscover.validators.RequestValidator;
import jakarta.validation.Valid;
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
@RequestMapping(value = "/flights")
public class FlightsController {

    private final FlightsFacade flightsFacade;

    @RequestMapping(value = "/getByToAndFrom", method = RequestMethod.GET)
    public ResponseEntity<List<FlightResponse>> findFlight(@Valid FlightRequest request) throws FlightNotFoundException {
        log.info("Find flight request {}", request);
        return ResponseEntity.ok(flightsFacade.getFlights(request));
    }

    @RequestMapping(value = "/getAllConnectionsBetween", method = RequestMethod.GET)
    public ResponseEntity<List<FlightResponse>> getAllConnectionsBetween(@Valid ConnectionRequest request) throws FlightNotFoundException {
        log.info("Check connections request {}", request);
        return ResponseEntity.ok(flightsFacade.getAllConnections(request));
    }
}
