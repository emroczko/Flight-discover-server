package com.tass.flightdiscover.controller;

import com.tass.flightdiscover.configuration.swagger.FlightsSwaggerConfiguration;
import com.tass.flightdiscover.domain.ErrorResponse;
import com.tass.flightdiscover.domain.flight.ConnectionRequest;
import com.tass.flightdiscover.domain.flight.Flight;
import com.tass.flightdiscover.domain.flight.FlightRequest;
import com.tass.flightdiscover.exceptions.BadRequestException;
import com.tass.flightdiscover.exceptions.FlightNotFoundException;
import com.tass.flightdiscover.facades.FlightsFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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
@RequestMapping(value = "/flights")
public class FlightsController {

    private final FlightsFacade flightsFacade;

    @FlightsSwaggerConfiguration
    @Operation(description = "Returns flights from and to given locations. If only one is given (to or from) then it returns all available flights for given location.")
    @GetMapping(value = "/getByToAndFrom")
    public ResponseEntity<List<Flight>> findFlight(@Valid @ParameterObject FlightRequest request) throws FlightNotFoundException, BadRequestException {
        log.info("Find flight request {}", request);
        return ResponseEntity.ok(flightsFacade.getFlights(request));
    }

    @FlightsSwaggerConfiguration
    @Operation(description = "Returns all connections between two locations and when locations are reversed")
    @GetMapping(value = "/getAllConnectionsBetween")
    public ResponseEntity<List<Flight>> getAllConnectionsBetween(@Valid @ParameterObject ConnectionRequest request) throws FlightNotFoundException {
        log.info("Check connections request {}", request);
        return ResponseEntity.ok(flightsFacade.getAllConnections(request));
    }
}
