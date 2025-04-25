package org.etg.service;

import org.etg.entity.Movie;
import org.etg.entity.MovieType;
import org.etg.service.impl.RegularRental;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class RentalStrategyFactoryTest {

    @Test
    void testRegularMovieStrategy() {
        Movie movie = new Movie("You've Got Mail", MovieType.REGULAR);
        RentalStrategy strategy = movie.getStrategy();

        assertInstanceOf(RegularRental.class, strategy);
        assertEquals(2.0, strategy.calculatePrice(1)); // Validate rental cost
        assertEquals(1, strategy.generatePoints(1)); // Validate frequent points
    }


}
