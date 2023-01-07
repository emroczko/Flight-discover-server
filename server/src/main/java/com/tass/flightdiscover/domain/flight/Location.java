package com.tass.flightdiscover.domain.flight;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Location {
    private String city;
    private String country;
}