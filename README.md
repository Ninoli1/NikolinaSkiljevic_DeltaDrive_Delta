# DeltaDrive

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [API Documentation](#api-documentation-with-swagger)

## Features
- **Authentication & Authorization**: Secured API endpoints with Spring Security and JWT tokens.
- **Database Integration**: PostgreSQL with [POSTGIS](https://postgis.net/documentation/getting_started/)  for handling spatial data and efficient querying.
- **Drive Simulation**: Visualizes the vehicle's route between start, passenger, and destination locations on an interactive map using Leaflet.js.
- **Optimized Location Search**: Finds the 10 nearest locations from a passenger's location using optimized SQL queries powered by [PostGIS geometry](https://postgis.net/docs/ST_Distance.html).

## Technologies Used
- **Backend**: Java Spring Boot, Spring Security (JWT)
- **Database**: PostgreSQL 
- **Map Rendering**: Leaflet.js, OpenStreetMap tiles
- **Build Tool**: Maven

## API Documentation with Swagger
This project includes API documentation generated using Swagger, a powerful tool for designing, building, documenting, and consuming RESTful APIs.

### Accessing Swagger UI
After starting the application, you can view the API documentation and interact with the available endpoints via Swagger UI. To access Swagger UI, open your browser and navigate to:

http://localhost:8080/swagger-ui.html


## API Access 
To access the methods of this application using Swagger, follow these steps:
1. **Register** using the endpoint in the authentication controller.
2. **Login** using the same controller.
3. **Copy** your JWT that you received in the login response.
4. Click on the **“Authorize”** button located at the top right.
5. Paste your token in the popup that appears and click **“Authorize”** to save the token.

Now, you can access any protected API endpoints. Select an endpoint from the Swagger UI, click **"Try it out"**, and execute the request. The token will be included automatically in the headers.

