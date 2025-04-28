package org.etg.service.impl;

import org.etg.entity.MovieRental;
import org.etg.entity.MovieType;
import org.etg.exception.MovieNotFoundException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class RentalCalculationServiceTest {
    private final RentalCalculationService calculationService = new RentalCalculationService();
    private final MovieService movieService = MovieService.getInstance();

    @Test
    void testRegularMovieCalculation() {
        MovieRental rental = new MovieRental("F006", 3); // 3-day rental of a regular movie
        movieService.addMovie("F006", "The Life List", MovieType.REGULAR);

        BigDecimal expectedAmount = BigDecimal.valueOf(3.50);
        assertEquals(0, calculationService.calculateAmount(rental).compareTo(expectedAmount)); // Compare BigDecimal safely
        assertEquals(1, calculationService.calculateFrequentPoints(rental)); // Regular movies always earn 1 point
    }

    @Test
    void testNewReleaseMovieCalculation() {
        MovieRental rental = new MovieRental("F004", 4); // 4-day rental of a new release movie
        movieService.addMovie("F004", "Fast & Furious X", MovieType.NEW_RELEASE);

        BigDecimal expectedAmount = BigDecimal.valueOf(12.00);
        assertEquals(0, calculationService.calculateAmount(rental).compareTo(expectedAmount)); // Compare BigDecimal safely
        assertEquals(2, calculationService.calculateFrequentPoints(rental)); // New releases earn 2 points if rented > 2 days
    }

    @Test
    void testChildrenMovieCalculation() {
        MovieRental rental = new MovieRental("F003", 5); // 5-day rental of a children's movie
        movieService.addMovie("F003", "Cars", MovieType.CHILDREN);

        BigDecimal expectedAmount = BigDecimal.valueOf(4.50);
        assertEquals(0, calculationService.calculateAmount(rental).compareTo(expectedAmount)); // Compare BigDecimal safely
        assertEquals(1, calculationService.calculateFrequentPoints(rental)); // Children's movies always earn 1 point
    }

    @Test
    void testInvalidMovieThrowsException() {
        MovieRental rental = new MovieRental("INVALID_ID", 3);

        Exception exception = assertThrows(MovieNotFoundException.class, () -> calculationService.getMovie(rental));
        assertEquals("Movie with ID: INVALID_ID not found.", exception.getMessage());
    }
}