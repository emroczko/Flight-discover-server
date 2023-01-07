package com.tass.flightdiscover.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightResponse {
    private Location from;
    private Location to;
    private Long numberOfFlights;

    @Data
    @Builder
    private static class Location {
        private String city;
        private String country;
    }
}
