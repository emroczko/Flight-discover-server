package com.tass.flightdiscover.domain.flight;

import com.tass.flightdiscover.validation.RequestValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@RequestValidator(message = "One of locations must be provided")
@AllArgsConstructor
@Builder
public class FlightRequest {
    private Location from;
    private Location to;
    private Boolean reverse;
}
