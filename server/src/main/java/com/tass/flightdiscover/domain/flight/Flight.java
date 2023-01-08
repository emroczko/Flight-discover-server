package com.tass.flightdiscover.domain.flight;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Flight {
    private Location from;
    private Location to;
    private Long numberOfFlights;
}
