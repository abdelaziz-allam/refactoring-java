package com.etraveli.service.rental;

import com.etraveli.enums.MovieType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RentalFactory {

  private final ChildrensMovieRentalCalculator childrenCalculator;
  private final NewReleaseMovieRentalCalculator newReleaseCalculator;
  private final RegularMovieRentalCalculator regularCalculator;

  public MovieRentalCalculator getCalculator(MovieType type) {
    return switch (type) {
      case REGULAR -> regularCalculator;
      case NEW -> newReleaseCalculator;
      case CHILDREN -> childrenCalculator;
    };
  }
}
