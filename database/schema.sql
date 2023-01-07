-- Table: public.cities

-- DROP TABLE IF EXISTS public.cities;

CREATE TABLE IF NOT EXISTS public.cities
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character varying(255) COLLATE pg_catalog."default",
    city_location_y double precision,
    city_location_x double precision,
    country_name character varying(255) COLLATE pg_catalog."default",
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
    total_flights_number_for_city_to_population_ratio double precision,
    CONSTRAINT cities_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.cities
    OWNER to flight_discover;

-- Table: public.flights

-- DROP TABLE IF EXISTS public.flights;

CREATE TABLE IF NOT EXISTS public.flights
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    origin_city character varying(255) COLLATE pg_catalog."default",
    origin_country character varying(255) COLLATE pg_catalog."default",
    destination_city character varying(255) COLLATE pg_catalog."default",
    destination_country character varying(255) COLLATE pg_catalog."default",
    number_of_flights bigint,
    CONSTRAINT flights_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.flights
    OWNER to flight_discover;