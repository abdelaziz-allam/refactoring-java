package com.etraveli.service.rental;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RegularMovieRentalCalculator implements MovieRentalCalculator{

  @Value("${rental.regular.amount}")
  private double amount;
  @Value("${rental.regular.factor}")
  private double factor;

  @Value("${rental.regular.days}")
  private int days;

  @Override
  public double calculateRentalAmount(int daysRented) {
    if (daysRented > days) {
      amount += (daysRented - days) * factor;
    }
    return amount;
  }

}
