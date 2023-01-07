import pandas as pd

FLIGHTS_FILEPATH = '/Users/erykmroczko/Downloads/flights.csv'
FLIGHTS_FILE_COLUMNS = ['ORIGIN_AIRPORT', 'DESTINATION_AIRPORT']
AIRPORTS_FILEPATH = '/Users/erykmroczko/Downloads/airports.csv'
AIRPORTS_FILE_COLUMNS = ['Name', 'City', 'Country', 'IATA']

CONNECTIONS_OUTPUT_FILEPATH = '/Users/erykmroczko/Downloads/connections_data1.csv'

airports_df = pd.read_csv(AIRPORTS_FILEPATH, usecols=AIRPORTS_FILE_COLUMNS).dropna()
airports_df.rename(columns={'Name': 'AIRPORT_NAME',
                            'City': 'CITY',
                            'Country': 'COUNTRY'}, inplace=True)

flights_df = pd.read_csv(FLIGHTS_FILEPATH, usecols=FLIGHTS_FILE_COLUMNS).dropna()
flights_df.rename(columns={'ORIGIN_AIRPORT': 'ORIGIN_AIRPORT_IATA',
                           'DESTINATION_AIRPORT': 'DESTINATION_AIRPORT_IATA'}, inplace=True)

flights_df = pd.merge(flights_df, airports_df, left_on=['ORIGIN_AIRPORT_IATA'], right_on=['IATA'])
flights_df.rename(columns={'AIRPORT_NAME': 'ORIGIN_AIRPORT_NAME',
                           'CITY': 'ORIGIN_CITY',
                           'COUNTRY': 'ORIGIN_COUNTRY'}, inplace=True)
flights_df = flights_df.drop(['IATA'], axis=1)

flights_df = pd.merge(flights_df, airports_df, left_on=['DESTINATION_AIRPORT_IATA'], right_on=['IATA'])
flights_df.rename(columns={'AIRPORT_NAME': 'DESTINATION_AIRPORT_NAME',
                           'CITY': 'DESTINATION_CITY',
                           'COUNTRY': 'DESTINATION_COUNTRY'}, inplace=True)

flights_df = flights_df.drop(['IATA'], axis=1)

flights_df = flights_df.groupby(['ORIGIN_CITY',
                                 'ORIGIN_COUNTRY',
                                 'DESTINATION_CITY',
                                 'DESTINATION_COUNTRY']).size().reset_index(name='NUMBER_OF_FLIGHTS')

flights_df.to_csv(CONNECTIONS_OUTPUT_FILEPATH, index=False)
