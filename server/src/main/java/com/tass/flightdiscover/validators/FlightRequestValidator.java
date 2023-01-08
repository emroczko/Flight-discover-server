package com.tass.flightdiscover.validators;

import com.tass.flightdiscover.domain.flight.FlightRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FlightRequestValidator implements ConstraintValidator<RequestValidator, FlightRequest> {

    @Override
    public boolean isValid(FlightRequest request, ConstraintValidatorContext context) {
        return !(request.getFrom() == null && request.getTo() == null);
    }
}
