package com.etraveli.service.rental;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ChildrenMovieRentalCalculatorTest {

  @Autowired
  private ChildrensMovieRentalCalculator calculator;

  @Test
  public void testCalculateRentalAmount() {
    int daysRented = 5;
    double amount = calculator.calculateRentalAmount(daysRented);
    assertEquals(4.5, amount);
  }
}