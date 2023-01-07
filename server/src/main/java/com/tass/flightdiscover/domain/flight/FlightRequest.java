package com.tass.flightdiscover.domain.flight;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlightRequest {
    private Location from;
    private Location to;
}
