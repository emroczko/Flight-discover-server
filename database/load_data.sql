COPY cities(name, city_location_y, city_location_x, country_name, city_population, flights_number_from_city, flights_number_to_city, origin_cities_number, destination_cities_number, total_connected_cities_number, origin_countries_number, destination_countries_number, busiest_month_flights_from_city, busiest_month_flights_to_city, flights_number_from_city_to_population_ratio, flights_number_to_city_to_population_ratio, total_flights_number_for_city_to_population_ratio)
FROM '/Users/erykmroczko/cities_data.csv'
DELIMITER ','
CSV HEADER;

COPY flights(origin_city, origin_country, destination_city, destination_country, number_of_flights)
FROM '/Users/erykmroczko/Downloads/connections_data.csv'
DELIMITER ','
CSV HEADER;