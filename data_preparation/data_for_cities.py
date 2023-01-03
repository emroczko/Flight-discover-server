import pandas as pd
import math


####################################################################################################


FLIGHTS_FILEPATH = 'data/raw/flights.csv'
FLIGHTS_FILE_COLUMNS = ['MONTH', 'DAY_OF_WEEK', 'ORIGIN_AIRPORT', 'DESTINATION_AIRPORT']
AIRPORTS_FILEPATH = 'data/raw/airports.csv'
AIRPORTS_FILE_COLUMNS = ['Name', 'City', 'Country', 'IATA', 'Longitude', 'Latitude']
CITIES_FILEPATH = 'data/raw/worldcities.csv'
CITIES_FILE_COLUMNS = ['city', 'country', 'population', 'lng', 'lat']

CITIES_OUTPUT_FILEPATH = 'data/prepared/cities_data.csv'


####################################################################################################

cities_df = pd.read_csv(CITIES_FILEPATH, usecols=CITIES_FILE_COLUMNS).dropna()
cities_df.rename(columns = {'city':'CITY', 'country':'COUNTRY', 'population':'CITY_POPULATION','lng': 'CITY_X', 'lat':'CITY_Y'}, inplace = True)

airports_df = pd.read_csv(AIRPORTS_FILEPATH, usecols=AIRPORTS_FILE_COLUMNS).dropna()
airports_df.rename(columns = {'Name':'AIRPORT_NAME', 'City':'CITY', 'Country':'COUNTRY','Longitude': 'AIRPORT_X', 'Latitude':'AIRPORT_Y'}, inplace = True)

airports_df = pd.merge(airports_df, cities_df, on=['CITY', 'COUNTRY'])

airports_df['TMP_AIRPORT_CITY_DISTANCE'] = airports_df.apply(lambda row: math.dist([row['AIRPORT_X'], row['AIRPORT_Y']], [row['CITY_X'], row['CITY_Y']]), axis=1)
airports_df = airports_df.loc[airports_df.groupby(airports_df.IATA)['TMP_AIRPORT_CITY_DISTANCE'].idxmin()]
airports_df = airports_df.drop(['TMP_AIRPORT_CITY_DISTANCE'], axis=1)

####################################################################################################

flights_df = pd.read_csv(FLIGHTS_FILEPATH, usecols=FLIGHTS_FILE_COLUMNS).dropna()
flights_df.rename(columns = {'ORIGIN_AIRPORT':'ORIGIN_AIRPORT_IATA', 'DESTINATION_AIRPORT':'DESTINATION_AIRPORT_IATA'}, inplace = True)

flights_df = pd.merge(flights_df, airports_df, left_on=['ORIGIN_AIRPORT_IATA'], right_on=['IATA'])
flights_df.rename(columns = {'AIRPORT_NAME':'ORIGIN_AIRPORT_NAME', 'AIRPORT_X': 'ORIGIN_AIRPORT_X', 'AIRPORT_Y': 'ORIGIN_AIRPORT_Y', 'CITY':'ORIGIN_CITY', 'CITY_X': 'ORIGIN_CITY_X', 'CITY_Y': 'ORIGIN_CITY_Y', 'COUNTRY':'ORIGIN_COUNTRY', 'CITY_POPULATION': 'ORIGIN_CITY_POPULATION'}, inplace = True)
flights_df = flights_df.drop(['IATA'], axis=1)

flights_df = pd.merge(flights_df, airports_df, left_on=['DESTINATION_AIRPORT_IATA'], right_on=['IATA'])
flights_df.rename(columns = {'AIRPORT_NAME':'DESTINATION_AIRPORT_NAME', 'AIRPORT_X': 'DESTINATION_AIRPORT_X', 'AIRPORT_Y': 'DESTINATION_AIRPORT_Y', 'CITY':'DESTINATION_CITY', 'CITY_X': 'DESTINATION_CITY_X', 'CITY_Y': 'DESTINATION_CITY_Y', 'COUNTRY':'DESTINATION_COUNTRY', 'CITY_POPULATION': 'DESTINATION_CITY_POPULATION'}, inplace = True)
flights_df = flights_df.drop(['IATA'], axis=1)
flights_df = flights_df[(flights_df.ORIGIN_CITY_X != flights_df.DESTINATION_CITY_X) | (flights_df.ORIGIN_CITY_Y != flights_df.DESTINATION_CITY_Y)]

####################################################################################################

def count_flights_from_city(flights_df, city_x, city_y):
    return len(flights_df[(flights_df.ORIGIN_CITY_X == city_x) & (flights_df.ORIGIN_CITY_Y == city_y)].index)

def count_flights_to_city(flights_df, city_x, city_y):
    return len(flights_df[(flights_df.DESTINATION_CITY_X == city_x) & (flights_df.DESTINATION_CITY_Y == city_y)].index)

cities_df['FLIGHTS_NUMBER_FROM_CITY'] = cities_df.apply(lambda row: count_flights_from_city(flights_df, row['CITY_X'], row['CITY_Y']), axis=1)
cities_df['FLIGHTS_NUMBER_TO_CITY'] = cities_df.apply(lambda row: count_flights_to_city(flights_df, row['CITY_X'], row['CITY_Y']), axis=1)

cities_df = cities_df[(cities_df.FLIGHTS_NUMBER_TO_CITY > 0) | (cities_df.FLIGHTS_NUMBER_FROM_CITY > 0)]

####################################################################################################

def count_origin_cities_for_given_city(flights_df, city_x, city_y):
    return flights_df[(flights_df.DESTINATION_CITY_X == city_x) & (flights_df.DESTINATION_CITY_Y == city_y)].groupby(['ORIGIN_CITY_X', 'ORIGIN_CITY_Y']).ngroups

def count_destination_cities_for_given_city(flights_df, city_x, city_y):
    return flights_df[(flights_df.ORIGIN_CITY_X == city_x) & (flights_df.ORIGIN_CITY_Y == city_y)].groupby(['DESTINATION_CITY_X', 'DESTINATION_CITY_Y']).ngroups

cities_df['ORIGIN_CITIES_NUMBER'] = cities_df.apply(lambda row: count_origin_cities_for_given_city(flights_df, row['CITY_X'], row['CITY_Y']), axis=1)
cities_df['DESTINATION_CITIES_NUMBER'] = cities_df.apply(lambda row: count_destination_cities_for_given_city(flights_df, row['CITY_X'], row['CITY_Y']), axis=1)

####################################################################################################

def count_origin_countries_for_given_city(flights_df, city_x, city_y):
    return len(flights_df[(flights_df.DESTINATION_CITY_X == city_x) & (flights_df.DESTINATION_CITY_Y == city_y)]['ORIGIN_COUNTRY'].unique())

def count_destination_countries_for_given_city(flights_df, city_x, city_y):
    return len(flights_df[(flights_df.ORIGIN_CITY_X == city_x) & (flights_df.ORIGIN_CITY_Y == city_y)]['DESTINATION_COUNTRY'].unique())

cities_df['ORIGIN_COUNTRIES_NUMBER'] = cities_df.apply(lambda row: count_origin_countries_for_given_city(flights_df, row['CITY_X'], row['CITY_Y']), axis=1)
cities_df['DESTINATION_COUNTRIES_NUMBER'] = cities_df.apply(lambda row: count_destination_countries_for_given_city(flights_df, row['CITY_X'], row['CITY_Y']), axis=1)

####################################################################################################

def get_busiest_month_according_to_flights_to_city(flights_df, city_x, city_y):
    flights_to_city_df = flights_df[(flights_df.DESTINATION_CITY_X == city_x) & (flights_df.DESTINATION_CITY_Y == city_y)]
    try:
        return flights_to_city_df['MONTH'].mode().item()
    except:
        return -1

def get_busiest_month_according_to_flights_from_city(flights_df, city_x, city_y):
    flights_from_city_df = flights_df[(flights_df.ORIGIN_CITY_X == city_x) & (flights_df.ORIGIN_CITY_Y == city_y)]
    try:
        return flights_from_city_df['MONTH'].mode().item()
    except:
        return -1

cities_df['BUSIEST_MONTH_FLIGHTS_TO_CITY'] = cities_df.apply(lambda row: get_busiest_month_according_to_flights_to_city(flights_df, row['CITY_X'], row['CITY_Y']), axis=1)
cities_df['BUSIEST_MONTH_FLIGHTS_FROM_CITY'] = cities_df.apply(lambda row: get_busiest_month_according_to_flights_from_city(flights_df, row['CITY_X'], row['CITY_Y']), axis=1)

####################################################################################################

def get_flights_to_population_ratio(flights_num, population):
    try:
        return flights_num / population
    except:
        return -1

def get_total_flights_to_population_ratio(flights_from_num, flights_to_num, population):
    try:
        return (flights_from_num + flights_to_num) / population
    except:
        return -1

cities_df['FLIGHTS_NUMBER_FROM_CITY_TO_POPULATION_RATIO'] = cities_df.apply(lambda row: get_flights_to_population_ratio(row['FLIGHTS_NUMBER_FROM_CITY'], row['CITY_POPULATION']), axis=1)
cities_df['FLIGHTS_NUMBER_TO_CITY_TO_POPULATION_RATIO'] = cities_df.apply(lambda row: get_flights_to_population_ratio(row['FLIGHTS_NUMBER_TO_CITY'], row['CITY_POPULATION']), axis=1)
cities_df['TOTAL_FLIGHTS_NUMBER_FOR_CITY_TO_POPULATION_RATIO'] = cities_df.apply(lambda row: get_total_flights_to_population_ratio(row['FLIGHTS_NUMBER_FROM_CITY'], row['FLIGHTS_NUMBER_TO_CITY'], row['CITY_POPULATION']), axis=1)

####################################################################################################

cities_df.rename(columns = {'CITY':'NAME', 'COUNTRY':'COUNTRY_NAME', 'CITY_X': 'CITY_LOCATION_X', 'CITY_Y':'CITY_LOCATION_Y'}, inplace = True)
cities_df['CITY_POPULATION'] = cities_df['CITY_POPULATION'].astype(int)

cities_df.to_csv(CITIES_OUTPUT_FILEPATH, index=False)