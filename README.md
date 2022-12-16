# E-Rental API 

E-Rental API, is an API for ordering car rentals online. Users must first register to be able to place an order, then make payments via e-wallet. As well as car rental management.

This project implements Spring IoC, Stream & Native Query.

The admin inputs car data with the default availability being true, then inputs the rental price and determines the car.

The customer registers, then logs in to be able to order a car, if a car with false availability means that car cannot be rented. Customers need to make payments via an integrated e-wallet, if the payment is successful then the paid status will become true and the payment date will be filled.

This API has several functions that need further improvement.
## Environment Variables

To run this project, I did a setup on the environment variable.

`DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USERNAME`, `DB_PASSWORD`, `API_PORT`, `URL_AUTH`, `JWT_SECRET`, `JWT_EXPIRATION`.
## USING API

In this project there is a dependency on swagger to do testing on the API. 

http://{host}:{port}/swagger-ui/index.html#/


ADMIN: 

- Car Management
- Price Management

CUSTOMER:
- Auth
- Transaction