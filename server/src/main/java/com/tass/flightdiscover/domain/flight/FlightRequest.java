package com.tass.flightdiscover.domain.flight;

import com.tass.flightdiscover.validators.RequestValidator;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@RequestValidator(message = "One of locations must be provided")
@AllArgsConstructor
public class FlightRequest {
    private Location from;
    private Location to;
    private Boolean reverse;
}
