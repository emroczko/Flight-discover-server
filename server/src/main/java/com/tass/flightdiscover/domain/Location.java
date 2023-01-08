package com.tass.flightdiscover.domain;

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
    //    @Parameter(in = ParameterIn.PATH, name = "name3", schema = @Schema(type = "string"))
    @NotBlank(message = "City cannot be null")
    private String city;
    //    @Parameter(in = ParameterIn.PATH, name = "name4", schema = @Schema(type = "string"))
    @NotBlank(message = "Country cannot be null")
    private String country;
}