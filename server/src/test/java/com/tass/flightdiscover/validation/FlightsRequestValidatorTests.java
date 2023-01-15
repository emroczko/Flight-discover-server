package com.tass.flightdiscover.validation;

import com.tass.flightdiscover.domain.flight.FlightRequest;
import com.tass.flightdiscover.domain.flight.Location;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FlightsRequestValidatorTests {

    @Autowired
    private Validator validator;

    @Test
    void testValidRequest() {
        var request = FlightRequest.builder()
                .to(Location.builder().build())
                .from(Location.builder().build())
                .build();

        var violations = validator.validate(request);
        assertEquals(0, violations.size());

        var anotherRequest = FlightRequest.builder()
                .to(Location.builder().build())
                .build();

        var anotherViolations = validator.validate(anotherRequest);
        assertEquals(0, anotherViolations.size());
    }

    @Test
    void testNotValidRequest() {
        var request = FlightRequest.builder()
                .build();

        var violations = validator.validate(request);
        assertEquals(1, violations.size());
    }
}
