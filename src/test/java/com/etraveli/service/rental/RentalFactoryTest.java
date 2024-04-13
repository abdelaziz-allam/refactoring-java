package com.etraveli.service.rental;

import com.etraveli.enums.MovieType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RentalFactoryTest {

  @Autowired
  private RentalFactory rentalFactory;

  @Autowired
  private ChildrensMovieRentalCalculator childrensCalculator;

  @Autowired
  private NewReleaseMovieRentalCalculator newReleaseCalculator;

  @Autowired
  private RegularMovieRentalCalculator regularCalculator;

  @Test
  public void testGetCalculatorForRegularMovie() {
    MovieRentalCalculator calculator = rentalFactory.getCalculator(MovieType.REGULAR);

    assertEquals(regularCalculator, calculator);
  }

  @Test
  public void testGetCalculatorForNewReleaseMovie() {
    MovieRentalCalculator calculator = rentalFactory.getCalculator(MovieType.NEW);

    assertEquals(newReleaseCalculator, calculator);
  }

  @Test
  public void testGetCalculatorForChildrenMovie() {
    MovieRentalCalculator calculator = rentalFactory.getCalculator(MovieType.CHILDREN);

    assertEquals(childrensCalculator, calculator);
  }
}
