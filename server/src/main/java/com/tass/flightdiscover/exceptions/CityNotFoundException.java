package com.tass.flightdiscover.exceptions;

public class CityNotFoundException extends Exception {
    public CityNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
