package com.tass.flightdiscover.configuration.swagger;

import com.tass.flightdiscover.domain.ErrorResponse;
import com.tass.flightdiscover.domain.flight.Flight;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found flights between or for locations",
                content = {@Content(mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = Flight.class)))}),
        @ApiResponse(responseCode = "404", description = "Flights not found",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
public @interface FlightsSwaggerConfiguration {
}
