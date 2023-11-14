# Booking Manager
Questions that I would ask before/after developing this project:
- Which data is needed for JSON responses? Which data a booking needs to have?
- Who is the client of the API? Auth is needed to access its resources?
- It will run inside docker containers? How should we handle the logging, metrics and observability?

## Booking Manager Application

The Booking Manager Application is a REST-based Java application designed to store and manage bookings and blocks of properties and guests. 

## Installation

### From Docker Hub image
Make sure you have the following mandatory software installed:
- Docker

To run the application, you only need to run the docker run.   
```docker run -p 8080:8080 viniciusgferreira/booking-manager```

### From local build
Clone this repository and build with maven   
```./mvnw clean package```   
After the maven build you must run:    
```java -jar target/booking-manager-0.0.1-SNAPSHOT.jar```   

## API Documentation
You can access the Swagger API docs with the application running.   
Click here -> [Documentation](http://localhost:8080/swagger-ui/index.html#/)

## Features
### A. Create, Rebook and Delete Bookings
The application allows users to store bookings. Users can create records of bookings with check-in and check-out dates, guest data and property data.   
Bookings dates cannot overlap Blocks or other Bookings.

### B. Create, Rebook and Delete Blocks
The application allows users to store bookings. Users can create records of bookings with start and end dates, reason and property data.   
Blocks dates can overlap each other be not overlap Bookings.

## Technologies Used
- Java 17
- Spring Boot 3.1.5
- H2 in-memory database

## Endpoints
### Manage Bookings
#### POST /api/bookings
- Create new booking   
`Request Body:` A JSON object with the booking data.   
`Responses:` 201 created, 409 conflict of dates.
#### POST /api/bookings/{uid}
- Rebook an existing booking   
  `Request Body:` A JSON object with the new booking dates.   
  `Responses:` 201 created, 409 conflict of dates.
#### GET /api/bookings/{uid}
- get existing booking data   
  `Request Path:` A uuid of the booking.   
  `Responses:` 200 OK, 404 not found.
#### PUT /api/bookings/{uid}
- Update or rebook an existing booking  
`Request Path:` A uuid of the booking.   
`Request Body:` A JSON object with the booking data.   
  `Responses:` 200 OK, 409 conflict of dates, 404 not found.
#### PATCH /api/bookings/{uid}
- Cancel an existing booking  
  `Request Path:` A uuid of the booking.   
  `Responses:` 200 OK, 404 not found.
#### DELETE /api/bookings/{uid}
- Delete an existing booking   
  `Request Path:` A uuid of the booking.   
  `Responses:` 204 No content, 404 not found.

### Manage Blocks
#### POST /api/blocks
- Create new block   
  `Request Body:` A JSON object with the block data.   
  `Responses:` 201 created, 409 conflict of dates.
#### GET /api/blocks/{uid}
- get existing block data   
  `Request Path:` A uuid of the block.   
  `Responses:` 200 OK, 404 not found.
#### PUT /api/blocks/{uid}
- Update an existing block  
  `Request Path:` A uuid of the block.   
  `Request Body:` A JSON object with the block data.   
  `Responses:` 200 OK, 409 conflict of dates, 404 not found.
#### DELETE /api/blocks/{uid}
- Delete an existing block   
  `Request Path:` A uuid of the block.   
  `Responses:` 204 No content, 404 not found.
