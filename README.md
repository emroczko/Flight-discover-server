# TASS-flight-discover

## PostgreSQL database preparation

Run the scripts in the following order:
1. database/role.sql
2. database/database.sql
3. database/schema.sql
4. database/load_data.sql (After possible adjustment of the path to the file. If you are using Windows, this file has to be in C:\Users\Public directory)

## Starting Spring Boot server

From the level of the server directory run:
> gradle bootRun

When the server is running you can go to http://localhost:8080/swagger-ui in web browser to see available endpoints with descriptions and use the convenient graphical interface to send HTTP requests and get responses.
