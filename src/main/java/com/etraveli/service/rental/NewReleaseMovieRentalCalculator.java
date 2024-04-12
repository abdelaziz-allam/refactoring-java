package com.etraveli.service.rental;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NewReleaseMovieRentalCalculator implements MovieRentalCalculator {

  @Value("${rental.new.amount}")
  private double amount;
  @Override
  public double calculateRentalAmount(int daysRented) {
    return daysRented * amount;
  }

}
