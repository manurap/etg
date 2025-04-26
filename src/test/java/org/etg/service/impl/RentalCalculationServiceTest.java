package org.etg.service.impl;

import org.etg.entity.Movie;
import org.etg.entity.MovieType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentalCalculationServiceTest {
    private final RentalCalculationService calculationService = new RentalCalculationService();

    @Test
    void testRegularMovieCalculation() {
        Movie movie = new Movie("You've Got Mail", MovieType.REGULAR);
        assertEquals(2.0, calculationService.calculateAmount(movie, 1)); // 2.0 for regular < 2 days
        assertEquals(3.5, calculationService.calculateAmount(movie, 3)); // 2 + ((3-2) * 1.5)
        assertEquals(1, calculationService.calculateFrequentPoints(movie, 3)); // Regular movies always give 1 point
    }

}
