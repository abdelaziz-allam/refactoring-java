# Refactoring Java

# eTraveli Movie Rental Back End Service

**eTraveli Movie Rental Service**  is a backend service that is used to handle customers data / movies data / customer rentals and their invoice statements 


## MOVIE-RENTAL API
     - POST /api/vi/rentals - Add a new customer rental.
     - GET /api/vi/rentals/{id} - Retrieve customer rental by customer Id.
     - GET /api/vi/rentals/statement/customer/{id} - Retrieve customer rental statement by customer id

## MOVIES API
     - POST /api/vi/movies - Save new movie
     - GET /api/vi/movies  - Fetch All Movies

## CUSTOMERS API
     - POST /api/vi/customers - Add a new customer.
     - GET /api/vi/customers -  Fetch All Customers


# Technology Stack
***eTraveli Movie Rental Service*** has been build with below java APIs
* Java 21
* Spring boot starters
* H2 As Production Database And As Testing Data base
* Aspjectj along with logback for logging
* jUnit - Mockito - Spring Testing as testing frameworks
* Jacco For Code Coverage
* Swagger For Rest API Documentation
* IntelliJ

# Project Structure



#### Code Level
* **src/main/java** holding all the classes used for handling the required business
* **src/main/resources** resource files that will be used by the main source code
* **src/test/java** holding all Integration and unit test classes
* **src/test/resources** resource files that will be used by testing classes


# How to run retail-product-catalog as Docker

* execute the blow command

```sh
    docker run -p 8090:8090  abdelazizallam/etravli-movie-rental-backend
```


# How to run etraveli movie rental Standalone

#### Build Project

- Make sure that you are on the retail-product-catalog dictory then start executing below commands
```sh
$ mvn clean install
$ java -jar target/movie-rental-0.0.1-SNAPSHOT.jar
```

# How to test champion-service APIS
- After successfully start
- Open browser click [swagger](http://localhost:8090/swagger-ui/)
- How swagger should look like ![swagger.png](images%2Fswagger.png)


## Enhancement Needed to be done
- Cover more test cases to cover all scenarios