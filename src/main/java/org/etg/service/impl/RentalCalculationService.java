package org.etg.service.impl;

import org.etg.entity.Movie;

/**
 * Rental calculation service
 */
public class RentalCalculationService {
    public double calculateAmount(Movie movie, int days) {
        return movie.getStrategy().calculatePrice(days);
    }

    public int calculateFrequentPoints(Movie movie, int days) {
        return movie.getStrategy().generatePoints(days);
    }

}
