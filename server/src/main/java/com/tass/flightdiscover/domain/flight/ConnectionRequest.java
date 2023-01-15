package com.tass.flightdiscover.domain.flight;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class ConnectionRequest {
    @NotNull(message = "First location cannot be null")
    private Location firstLocation;
    @NotNull(message = "Second location cannot be null")
    private Location secondLocation;
}
