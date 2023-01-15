# TASS-flight-discover

## Data preparation

Download the data from links provided in project documentation and place files in the data/raw directory. After that run scripts from main directory using command:
> python data_preparation/script_name.py

## PostgreSQL database preparation

Run the scripts in the following order:
1. database/role.sql
2. database/database.sql
3. database/schema.sql
4. database/load_data.sql (After adjustment of the path to the file. File must be in public directory - for example C:\Users\Public directory on Windows systems)

## Starting Spring Boot server

From the level of the server directory run:
> gradle bootRun

When the server is running you can go to http://localhost:8080/swagger-ui in web browser to see available endpoints with descriptions and use the convenient graphical interface to send HTTP requests and get responses.
