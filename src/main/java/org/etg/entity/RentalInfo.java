package org.etg.entity;

import org.etg.service.RentalStrategy;
import org.etg.service.impl.RegularRental;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * processing moving rental information
 */
public class RentalInfo {
  private static final Map<String, Movie> movies = Map.of(
          "F001", new Movie("You've Got Mail", MovieType.REGULAR),
          "F002", new Movie("Matrix", MovieType.REGULAR),
          "F003", new Movie("Cars", MovieType.CHILDREN),
          "F004", new Movie("Fast & Furious X", MovieType.NEW_RELEASE)
  );


  public String statement(Customer customer) {
    double totalAmount = 0;
    int frequentEnterPoints = 0;
    StringBuilder result = new StringBuilder("Rental Record for " + customer.name() + "\n");

    for (MovieRental rental : customer.rentals()) {
      Movie movie = movies.get(rental.movieId());
      RentalStrategy strategy = movie.getStrategy();

      double amount = strategy.calculatePrice(rental.days());
      int points = strategy.generatePoints(rental.days());

      frequentEnterPoints += points;
      result.append("\t").append(movie.title()).append("\t").append(amount).append("\n");
      totalAmount += amount;
    }

    result.append("Amount owed is ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");

    return result.toString();
  }
}
