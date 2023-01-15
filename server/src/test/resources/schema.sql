DROP TABLE IF EXISTS cities;
CREATE TABLE IF NOT EXISTS cities
(
    id bigint,
    name character varying(255),
    city_location_y double precision,
    city_location_x double precision,
    country_name character varying(255),
    city_population bigint,
    flights_number_from_city bigint,
    flights_number_to_city bigint,
    origin_cities_number bigint,
    destination_cities_number bigint,
    total_connected_cities_number bigint,
    origin_countries_number bigint,
    destination_countries_number bigint,
    busiest_month_flights_from_city integer,
    busiest_month_flights_to_city integer,
    flights_number_from_city_to_population_ratio double precision,
    flights_number_to_city_to_population_ratio double precision,
    total_flights_number_for_city_to_population_ratio double precision
);

CREATE TABLE IF NOT EXISTS flights
(
    id bigint NOT NULL,
    origin_city character varying(255),
    origin_country character varying(255),
    destination_city character varying(255),
    destination_country character varying(255),
    number_of_flights bigint
);