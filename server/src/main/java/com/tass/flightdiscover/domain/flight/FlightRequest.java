package com.tass.flightdiscover.domain.flight;

import com.tass.flightdiscover.domain.Location;
import com.tass.flightdiscover.validators.RequestValidator;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@RequestValidator(message = "One of locations must be provided")
@AllArgsConstructor
public class FlightRequest {
    @Parameter(in = ParameterIn.PATH, name = "name", schema = @Schema(type = "location"))
    private Location from;
    //    @Parameter(in = ParameterIn.PATH, name = "name1", schema = @Schema(type = "location"))
    private Location to;
    //    @Parameter(in = ParameterIn.PATH, name = "name2", schema = @Schema(type = "boolean"))
    private Boolean reverse;
}
