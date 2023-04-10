# Thermostat Management System

The project contains 3 applications:

1) `tms` - Thermostat Management Service API
2) `tms-dashboard` - Thermostat Management Service Dashboard
3) `feeder` - Console application that feeds temperature data to TMS

TMS is documented using Swagger. After running the TMS API, you can find the Swagger documentation at:
http://localhost:8080/swagger-ui

## TMS

### Authentication

TMS uses JWT for authentication. To get a JWT token, you need to send a POST request to `/auth/login` with a username
and password in the request body.

Users are pre-inserted in the database. Here's the list:

| Email                    | Password |
|--------------------------|----------|
| skinnyeric@gmail.com     | 12345678 |
| feminineandrew@gmail.com | 12345678 |
| datafeeder@gmail.com     | 12345678 |

Note that `datafeeder` is a special user; only they can feed data to TMS.

The JWT token is valid for 1 day. After that, you need to get a new token.

The application uses asymmetric encryption to generate JWT tokens. You can find the public and private keys in
the `tms/src/main/resources/certs` directory. The `keypair.pem` file was used to generate public and private keys.

### Database

The application uses a PostgreSQL database. You can find the database schema
in `tms/src/main/resources/db/migration/V1__init.sql`. To run the database locally, you can use the `docker-compose`
file in the project's root directory. To run the database, execute: docker-compose up -d

### Swagger

## API Endpoints

| Method | Endpoint                                          | Description                                  |
|--------|---------------------------------------------------|----------------------------------------------|
| GET    | `/api/v1/user/thermostats`                        | Get user's thermostats                       |
| POST   | `/api/v1/user/thermostats`                        | Create a thermostat for the user             |
| PUT    | `/api/v1/user/thermostats/{id}`                   | Update the thermostat with the given ID      |
| DELETE | `/api/v1/user/thermostats/{id}`                   | Delete the thermostat with the given ID      |
| GET    | `/api/v1/thermostats`                             | Get all thermostats                          |
| GET    | `/api/v1/thermostats/{id}`                        | Get thermostat with the given ID             |
| GET    | `/api/v1/thermostats/id`                          | Get thermostat IDs                           |
| POST   | `/api/v1/thermostats/{thermostatId}/temperatures` | Register temperature data for the thermostat |
| POST   | `/api/v1/auth/token`                              | Authenticate user and get access token       |
| GET    | `/api/v1/user`                                    | Get current user details                     |

## TMS Dashboard

A simple Angular application to visualize and manage thermostats.

The application will prompt for an access token. You can get an access token by sending a POST request to `/auth/login`
with a username and password in the request body.

The application assumes that the TMS API is running on `localhost:8080`.

## Feeder

Feeder is a console application that feeds temperature data to TMS.

First, it authenticates with TMS and gets an access token. Then, it retrieves thermostat IDs and feeds temperature data.
It takes 3 parameters:

    n -> Number of temperature data points per thermostat (default: 10)
    min -> Minimum temperature degree (default: 10)
    max -> Maximum temperature degree (default: 30)

The application will generate n temperatures between min and max for each thermostat and send them to TMS.

The application assumes that the TMS API is running on localhost:8080.

P.s APIS are written on Java 17, so you need to have Java 17 installed on your machine.