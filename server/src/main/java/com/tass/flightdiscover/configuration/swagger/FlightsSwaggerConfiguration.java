package com.tass.flightdiscover.configuration.swagger;

import com.tass.flightdiscover.domain.ErrorResponse;
import com.tass.flightdiscover.domain.flight.FlightResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found fights between or for locations",
                content = {@Content(mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = FlightResponse.class)))}),
        @ApiResponse(responseCode = "404", description = "Flights not found",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
public @interface FlightsSwaggerConfiguration {
}
