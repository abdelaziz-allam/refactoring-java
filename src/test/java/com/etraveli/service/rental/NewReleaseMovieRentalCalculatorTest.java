package com.etraveli.service.rental;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NewReleaseMovieRentalCalculatorTest {

  @Autowired
  private NewReleaseMovieRentalCalculator calculator;
  @Test
  void testCalculateRentalAmount() {
    int daysRented = 5;
    double amount = calculator.calculateRentalAmount(daysRented);
    assertEquals(7.5, amount);
  }
}