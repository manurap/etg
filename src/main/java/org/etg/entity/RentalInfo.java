package org.etg.entity;

import java.util.HashMap;

/**
 * processing moving rental information
 */
public class RentalInfo {

  public String statement(Customer customer) {
    HashMap<String, Movie> movies = new HashMap<>();
    movies.put("F001", new Movie("You've Got Mail", MovieType.REGULAR));
    movies.put("F002", new Movie("Matrix", MovieType.REGULAR));
    movies.put("F003", new Movie("Cars", MovieType.CHILDREN));
    movies.put("F004", new Movie("Fast & Furious X", MovieType.NEW_RELEASE));

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    String result = "Rental Record for " + customer.name() + "\n";
    for (MovieRental r : customer.rentals()) {
      double thisAmount = 0;

      // determine amount for each movie
      if (movies.get(r.movieId()).movieType()== MovieType.REGULAR) {
        thisAmount = 2;
        if (r.days() > 2) {
          thisAmount = ((r.days() - 2) * 1.5) + thisAmount;
        }
      }
      if (movies.get(r.movieId()).movieType()== MovieType.NEW_RELEASE) {
        thisAmount = r.days() * 3;
      }
      if (movies.get(r.movieId()).movieType()== MovieType.CHILDREN) {
        thisAmount = 1.5;
        if (r.days() > 3) {
          thisAmount = ((r.days() - 3) * 1.5) + thisAmount;
        }
      }

      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (movies.get(r.movieId()).movieType() == MovieType.NEW_RELEASE && r.days() > 2) frequentEnterPoints++;

      //print figures for this rental
      result += "\t" + movies.get(r.movieId()).title() + "\t" + thisAmount + "\n";
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentEnterPoints + " frequent points\n";

    return result;
  }
}
