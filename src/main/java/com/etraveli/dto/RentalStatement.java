package com.etraveli.dto;

import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record RentalStatement(String customerName,String movieTitle,double totalAmount, int frequentEnterPoints) {
  private static final String LINE_SEPARATOR = System.lineSeparator();
  private static final String TAB = "\t";
  @Override
  public String toString() {
    String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";
    return "Rental Record for " +customerName
            + LINE_SEPARATOR + TAB
            + "You've Got Mail" + TAB
            + "value" + LINE_SEPARATOR + TAB
            + "Matrix" + TAB
            + "value" + LINE_SEPARATOR
            + "Amount owed is "
            + "value" + LINE_SEPARATOR
            + "You earned " + "value"
            + "frequent points" + LINE_SEPARATOR;
  }

  public static String toString(List<RentalStatement> rentalStatements) {
    return rentalStatements.stream()
            .map(RentalStatement::toString)
            .collect(Collectors.joining(LINE_SEPARATOR));
  }
}
