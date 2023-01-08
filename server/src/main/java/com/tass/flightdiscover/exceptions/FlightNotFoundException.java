package com.tass.flightdiscover.exceptions;

public class FlightNotFoundException extends Exception {
    public FlightNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
