package org.etg.entity;

import org.etg.service.RentalStrategy;
import org.etg.service.impl.RentalCalculationService;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * processing moving rental information
 */
public class RentalInfo {
  private final RentalCalculationService calculationService = new RentalCalculationService();
  private static final Map<String, Movie> movies = Map.of(
          "F001", new Movie("You've Got Mail", MovieType.REGULAR),
          "F002", new Movie("Matrix", MovieType.REGULAR),
          "F003", new Movie("Cars", MovieType.CHILDREN),
          "F004", new Movie("Fast & Furious X", MovieType.NEW_RELEASE)
  );

  public String statement(Customer customer) {
    var rentalDetails = customer.rentals().stream()
            .map(rental -> {
              Movie movie = movies.get(rental.movieId());
              double amount = calculationService.calculateAmount(movie, rental.days());
              int points = calculationService.calculateFrequentPoints(movie, rental.days());

              return new Object() {
                final double totalAmount = amount;
                final int frequentPoints = points;
                final String details = "\t" + movie.title() + "\t" + amount + "\n";
              };
            })
            .toList();

    // Calculate totals
    double totalAmount = rentalDetails.stream().mapToDouble(r -> r.totalAmount).sum();
    int frequentEnterPoints = rentalDetails.stream().mapToInt(r -> r.frequentPoints).sum();
    String details = rentalDetails.stream().map(r -> r.details).collect(Collectors.joining());

    return "Rental Record for " + customer.name() + "\n" +
            details +
            "Amount owed is " + totalAmount + "\n" +
            "You earned " + frequentEnterPoints + " frequent points\n";
  }
}
