package com.tass.flightdiscover.domain.flight;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @NotBlank(message = "City cannot be null")
    private String city;
    @NotBlank(message = "Country cannot be null")
    private String country;
}