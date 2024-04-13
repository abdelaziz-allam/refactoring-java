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

![code-structure.png](images%2Fcode-structure.png)


# How to run code at your local 
- clone code base from github
- get the source code at your preferred IDES preference Intellj
- make sure your application configuration pointing to Application File 


# How to run etraveli movie rental Standalone

#### Build Project

- Make sure that you are on the refactoring-java directory then start executing below commands
```sh
$ mvn clean install
$ java -jar target/movie-rental-0.0.1-SNAPSHOT.jar
```

# How to test movie-rental-service APIS
- After successfully start
- Open browser click [swagger](http://localhost:8090/swagger-ui/)
- How swagger should look like ![swagger.png](images%2Fswagger.png)

# Code Coverage 

![test-coverage.png](images%2Ftest-coverage.png)

## Enhancement Needed to be done
- Handle Different Exceptions and add code coverage over them
- Build Docker Image for the backend service 