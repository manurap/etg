package org.etg.service.impl;

import org.etg.entity.Movie;
import org.etg.entity.MovieRental;

import java.math.BigDecimal;

/**
 * Service for calculating rental pricing and frequent renter points.
 * <p>
 * This service retrieves movies and applies the corresponding rental strategy
 * to compute rental costs and rewards based on movie type and rental duration.
 * </p>
 */
public class RentalCalculationService {
    private final MovieService movieService = MovieService.getInstance();

    /**
     * Calculates the total rental cost for a given movie rental.
     * @param rental The rental details containing the movie ID and rental duration
     * @return The total rental cost as a BigDecimal
     */
    public BigDecimal calculateAmount(MovieRental rental) {
        return getMovie(rental).getStrategy().calculatePrice(rental.days());
    }

    /**
     * Determines the frequent renter points earned for a given rental and rental duration
     * @param rental The rental details containing the movie ID and rental
     * @return The frequent renter points earned.
     */
    public int calculateFrequentPoints(MovieRental rental) {
        return getMovie(rental).getStrategy().generatePoints(rental.days());
    }

    /**
     * Retrieves the movie corresponding to a given rental.
     * @param movieRental
     * @return The corresponding {@link Movie} object.
     * @throws org.etg.exception.MovieNotFoundException if the movie ID is not found
     */
    public Movie getMovie(MovieRental movieRental){
        return movieService.getMovie(movieRental.movieId());
    }
}
