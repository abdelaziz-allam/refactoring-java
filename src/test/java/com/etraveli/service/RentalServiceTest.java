package com.etraveli.service;

import com.etraveli.dto.*;
import com.etraveli.dto.request.SaveRental;
import com.etraveli.dto.request.SaveRentalBuilder;
import com.etraveli.entity.Customer;
import com.etraveli.entity.Movie;
import com.etraveli.entity.MovieRental;
import com.etraveli.enums.MovieType;
import com.etraveli.repo.CustomerRepository;
import com.etraveli.repo.MovieRentalRepository;
import com.etraveli.repo.MovieRepository;
import com.etraveli.service.rental.RegularMovieRentalCalculator;
import com.etraveli.service.rental.RentalFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RentalServiceTest {
  @Mock
  private CustomerRepository customerRepository;
  @Mock
  private MovieRepository movieRepository;
  @Mock
  private MovieRentalRepository rentalRepository;
  @Mock
  private RentalFactory rentalFactory;
  @Mock
  private RegularMovieRentalCalculator regularCalculator;
  @InjectMocks
  private RentalService rentalService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }


  @Test
  void testSaveCustomerRental() {
    UUID customerId = UUID.randomUUID();
    UUID movieId = UUID.randomUUID();
    SaveRental request = SaveRentalBuilder.builder().customerId(customerId).movieId(movieId).days(5).build();
    RentalInfo expected = RentalInfoBuilder.builder().customerId(customerId).customerName("Abdelaziz Allam")
            .movieName("You've Got Mail").type(MovieType.REGULAR).days(5).build();
    Customer customer = new Customer(customerId, "Abdelaziz Allam",new ArrayList<>());
    Movie movie = new Movie(movieId, "F001","You've Got Mail", MovieType.REGULAR);
    MovieRental rental = new MovieRental(UUID.randomUUID(),customer,movie, 5);

    when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
    when(movieRepository.findById(any())).thenReturn(Optional.of(movie));
    when(rentalRepository.save(any())).thenReturn(rental);

    RentalInfo response = rentalService.saveCustomerRental(request);

    assertNotNull(response);
    assertThat(expected, is(response));
  }

  @Test
  void testGetCustomerRental() {
    UUID customerId = UUID.randomUUID();
    UUID movieId = UUID.randomUUID();
    List<MovieRental> entities = List.of(
            new MovieRental(UUID.randomUUID(),
                    new Customer(customerId,"Abdelaziz Allam",null),
                    new Movie(movieId,"F001","You've Got Mail",MovieType.REGULAR),5)
    );
    List<MovieRentalDTO> expected = List.of( MovieRentalDTOBuilder.builder()
            .days(5)
            .customer(CustomerDTOBuilder.builder()
                    .id(customerId)
                    .name("Abdelaziz Allam")
                    .build())
            .movie(MovieDTOBuilder.builder()
                    .id(movieId)
                    .title("You've Got Mail")
                    .code("F001")
                    .type(MovieType.REGULAR)
                    .build())
            .build());
    when(rentalRepository.findByCustomerId(customerId)).thenReturn(entities);
    List<MovieRentalDTO>  response = rentalService.getCustomerRental(customerId);
    assertThat(Objects.requireNonNull(expected), hasSize(response.size()));
  }

  @Test
  void testStatement() {
    UUID customerId = UUID.randomUUID();
    UUID movieId = UUID.randomUUID();
    String statement = """
Rental Record for Abdelaziz Allam
	You've Got Mail	7.5
Amount owed is 7.5
You earned 1 frequent points
            """;

    List<MovieRental> entities = List.of(
            new MovieRental(UUID.randomUUID(),
                    new Customer(customerId,"Abdelaziz Allam",null),
                    new Movie(movieId,"F001","You've Got Mail",MovieType.REGULAR),5)
    );
    Customer customer = new Customer(customerId, "Abdelaziz Allam",entities);

    when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
    when(rentalFactory.getCalculator(MovieType.REGULAR)).thenReturn(regularCalculator);
    when(regularCalculator.calculateRentalAmount(5)).thenReturn(7.5);

    String expectedStatement = rentalService.statement(customerId);

    assertEquals(expectedStatement,statement);
  }

}