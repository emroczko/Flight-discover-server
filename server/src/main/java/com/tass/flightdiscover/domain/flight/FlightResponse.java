package com.tass.flightdiscover.domain.flight;

import com.tass.flightdiscover.domain.Location;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightResponse {
    private Location from;
    private Location to;
    private Long numberOfFlights;
}
