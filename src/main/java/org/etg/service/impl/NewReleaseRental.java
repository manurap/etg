package org.etg.service.impl;

import org.etg.service.RentalStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Rental strategy for NewRelease's movies.
 */
public class NewReleaseRental implements RentalStrategy {
    private static final BigDecimal PRICE_PER_DAY = BigDecimal.valueOf(3);
    private static final int MIN_DAYS_FOR_EXTRA_POINT = 2;

    /**
     * Calculates the total rental price for a New Release's movie.
     *
     * @param days The number of days the movie is rented.
     * @return The total rental price, rounded to two decimal places.
     */
    @Override
    public BigDecimal calculatePrice(int days) {
        if (days <= 0) {
            return BigDecimal.ZERO;
        }
        return PRICE_PER_DAY.multiply(BigDecimal.valueOf(days)).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Calculates the frequent rental points based on the rental duration.
     *
     * @param days The number of days the movie is rented.
     * @return The frequent rental points earned.
     */
    @Override
    public int generatePoints(int days) {
        if (days <= 0) {
            return 0;
        }

        int frequentEnterPoints = 1;
        if (days > MIN_DAYS_FOR_EXTRA_POINT) {
            frequentEnterPoints++;
        }

        return frequentEnterPoints;
    }
}
