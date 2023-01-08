package com.tass.flightdiscover.configuration.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
        info = @Info(
                title = "CitiesDiscoverAPI",
                description = """
                        This API allows to discover tourist cities, hubs and other locations from retrieved flights data.
                        It also allows to check if cities has direct flight connections with other cities.
                        """),
        servers = @Server(url = "http://localhost:8080")
)
public class SwaggerConfig {
}
