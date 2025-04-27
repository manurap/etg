package org.etg.service.impl;

import org.etg.entity.Movie;
import org.etg.entity.MovieRental;
import org.etg.entity.MovieType;
import org.etg.exception.MovieNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RentalCalculationServiceTest {
    private final RentalCalculationService calculationService = new RentalCalculationService();
    private final MovieService movieService = MovieService.getInstance();

    @Test
    void testRegularMovieCalculation() {
        MovieRental rental = new MovieRental("F006", 3); // 3-day rental of a regular movie
        movieService.addMovie("F006","The Life List", MovieType.REGULAR);

        assertEquals(3.5, calculationService.calculateAmount(rental)); // Expected: 2 + (3-2)*1.5
        assertEquals(1, calculationService.calculateFrequentPoints(rental)); // Regular movies always earn 1 point
    }

    @Test
    void testNewReleaseMovieCalculation() {
        MovieRental rental = new MovieRental("F004", 4); // 4-day rental of a new release movie
        Movie movie = new Movie("Fast & Furious X", MovieType.NEW_RELEASE);

        assertEquals(12.0, calculationService.calculateAmount(rental)); // Expected: 4 days * 3.0
        assertEquals(2, calculationService.calculateFrequentPoints(rental)); // New releases earn 2 points if rented > 2 days
    }

    @Test
    void testChildrenMovieCalculation() {
        MovieRental rental = new MovieRental("F003", 5); // 5-day rental of a children's movie
        Movie movie = new Movie("Cars", MovieType.CHILDREN);

        assertEquals(4.5, calculationService.calculateAmount(rental)); // Expected: 1.5 + (5-3) * 1.5
        assertEquals(1, calculationService.calculateFrequentPoints(rental)); // Children's movies always earn 1 point
    }

    @Test
    void testInvalidMovieThrowsException() {
        MovieRental rental = new MovieRental("INVALID_ID", 3);

        Exception exception = assertThrows(MovieNotFoundException.class, () -> calculationService.getMovie(rental));
        assertEquals("Movie with ID: INVALID_ID not found.", exception.getMessage());
    }

}
