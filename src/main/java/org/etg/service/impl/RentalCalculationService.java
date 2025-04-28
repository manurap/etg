package org.etg.service.impl;

import org.etg.entity.Movie;
import org.etg.entity.MovieRental;

import java.math.BigDecimal;

/**
 * Rental calculation service
 */
public class RentalCalculationService {
    private final MovieService movieService = MovieService.getInstance();

    public BigDecimal calculateAmount(MovieRental rental) {
        return getMovie(rental).getStrategy().calculatePrice(rental.days());
    }

    public int calculateFrequentPoints(MovieRental rental) {
        return getMovie(rental).getStrategy().generatePoints(rental.days());
    }

    public Movie getMovie(MovieRental movieRental){
        return movieService.getMovie(movieRental.movieId());
    }
}
