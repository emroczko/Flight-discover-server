package com.tass.flightdiscover.domain.flight;

import com.tass.flightdiscover.domain.Location;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ConnectionRequest {
    @NotNull(message = "First location cannot be null")
    private Location firstLocation;
    @NotNull(message = "Second location cannot be null")
    private Location secondLocation;
}
