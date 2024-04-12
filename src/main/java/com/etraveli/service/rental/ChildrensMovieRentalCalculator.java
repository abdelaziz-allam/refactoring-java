package com.etraveli.service.rental;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChildrensMovieRentalCalculator implements MovieRentalCalculator {

  @Value("${rental.children.amount}")
  private double amount;
  @Value("${rental.children.factor}")
  private double factor;

  @Value("${rental.children.days}")
  private int days;

  @Override
  public double calculateRentalAmount(int daysRented) {
    if (daysRented > days) {
      amount += (daysRented - days) * factor;
    }
    return amount;
  }
}
