package com.etraveli.service;

import com.etraveli.dto.*;
import com.etraveli.dto.request.SaveRental;
import com.etraveli.entity.Customer;
import com.etraveli.entity.Movie;
import com.etraveli.entity.MovieRental;
import com.etraveli.enums.MovieType;
import com.etraveli.repo.CustomerRepository;
import com.etraveli.repo.MovieRentalRepository;
import com.etraveli.repo.MovieRepository;
import com.etraveli.service.rental.MovieRentalCalculator;
import com.etraveli.service.rental.RentalFactory;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.etraveli.constant.AppConstants.CUSTOMER_NOT_FOUND;
import static com.etraveli.constant.AppConstants.MOVIE_NOT_FOUND;

@Service
public class RentalService {
  private final CustomerRepository customerRepository;
  private final MovieRepository movieRepository;
  private final MovieRentalRepository movieRentalRepository;
  private final RentalFactory rentalFactory;

  @Value("${rental.frequent.days}")
  private int days;

    public RentalService(CustomerRepository customerRepository, MovieRepository movieRepository, MovieRentalRepository movieRentalRepository, RentalFactory rentalFactory) {
        this.customerRepository = customerRepository;
        this.movieRepository = movieRepository;
        this.movieRentalRepository = movieRentalRepository;
        this.rentalFactory = rentalFactory;
    }

    @Transactional
  @PostConstruct
  public void init() {
    Movie movie1 = new Movie(UUID.randomUUID(), "F001", "Harry potter", MovieType.CHILDREN);
    Movie movie2 = new Movie(UUID.randomUUID(), "F002", "The Avengers", MovieType.REGULAR);
    Customer customer = new Customer(UUID.randomUUID(), "C. U. Stomer", null);
    Customer customer2 = new Customer(UUID.randomUUID(), "C. U. Stomer2", null);
    movieRepository.saveAll(List.of(movie1, movie2));
    customerRepository.saveAll(List.of(customer, customer2));
  }

  public RentalInfo saveCustomerRental(SaveRental request) {
    Customer customer = customerRepository.findById(request.customerId())
            .orElseThrow(() -> new IllegalArgumentException(CUSTOMER_NOT_FOUND));
    Movie movie = movieRepository.findById(request.movieId())
            .orElseThrow(() -> new IllegalArgumentException(MOVIE_NOT_FOUND));
    MovieRental rental = new MovieRental(UUID.randomUUID(),customer,movie, request.days());
    movieRentalRepository.save(rental);
    return RentalInfoBuilder.builder()
            .customerId(customer.getId())
            .customerName(customer.getName())
            .movieName(movie.getTitle())
            .type(movie.getType())
            .days(rental.getDays())
            .build();
  }


  public List<MovieRentalDTO> getCustomerRental(UUID customerId) {
    List<MovieRentalDTO> rentals = new ArrayList<>();
    List<MovieRental> entities = movieRentalRepository.findByCustomerId(customerId);
    entities.forEach(
            rental -> rentals.add(MovieRentalDTOBuilder.builder()
                    .movie(MovieDTOBuilder.builder().title(rental.getMovie().getTitle()).build())
                    .customer(CustomerDTOBuilder.builder().name(rental.getCustomer().getName()).build())
                    .days(rental.getDays())
                    .build()));
    return rentals;
  }
  public String statement(UUID customerId) {
    Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException(CUSTOMER_NOT_FOUND));
    List<RentalStatement> statements = new ArrayList<>();
    double totalAmount = 0;
    int frequentEnterPoints = 0;

    for (MovieRental rental : customer.getRentals()) {
      MovieRentalCalculator calculator = rentalFactory.getCalculator(rental.getMovie().getType());
      double rentalAmount = calculator.calculateRentalAmount(rental.getDays());

      // Increment frequent enter points
      frequentEnterPoints++;
      if (rental.getMovie().getType() == MovieType.NEW && rental.getDays() > days) {
        frequentEnterPoints++;
      }

      totalAmount += rentalAmount;
      statements.add(new RentalStatement(customer.getName(), rental.getMovie().getTitle(), rentalAmount, frequentEnterPoints));
    }

    return generateStatementString(customer.getName(), statements, totalAmount, frequentEnterPoints);
  }

  private String generateStatementString(String customerName, List<RentalStatement> statements, double totalAmount, int frequentEnterPoints) {
    StringBuilder result = new StringBuilder(STR."Rental Record for \{customerName}\n");

    for (RentalStatement statement : statements) {
      result.append("\t").append(statement.movieTitle()).append("\t").append(statement.totalAmount()).append("\n");
    }

    result.append("Amount owed is ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");

    return result.toString();

  }


}
