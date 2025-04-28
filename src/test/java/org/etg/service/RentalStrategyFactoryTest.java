package org.etg.service;

import org.etg.entity.Movie;
import org.etg.entity.MovieType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentalStrategyFactoryTest {

    @Test
    void testRegularMovieStrategy() {
        Movie movie = new Movie("You've Got Mail", MovieType.REGULAR);
        RentalStrategy strategy = movie.getStrategy();

        BigDecimal expectedPrice = new BigDecimal("2.0");
        BigDecimal actualPrice = strategy.calculatePrice(1);

        assertEquals(0, actualPrice.compareTo(expectedPrice));// Validate rental cost
        assertEquals(1, strategy.generatePoints(1)); // Validate frequent points
    }
}
