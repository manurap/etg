package org.etg.service.impl;

import org.etg.service.RentalStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Rental strategy for children's movies.
 */
public class ChildrenRental implements RentalStrategy {
    private static final BigDecimal DEFAULT_PRICE = BigDecimal.valueOf(1.5);
    private static final BigDecimal PRICE_PER_DAY = BigDecimal.valueOf(1.5);
    private static final int MAX_DAYS_FOR_DEFAULT_PRICE = 3;

    /**
     * Calculates the total rental price for a children's movie.
     *
     * @param days The number of days the movie is rented.
     * @return The total rental price, rounded to two decimal places.
     */
    @Override
    public BigDecimal calculatePrice(int days) {
        if (days <= 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal amount = DEFAULT_PRICE;
        if (days > MAX_DAYS_FOR_DEFAULT_PRICE) {
            amount = amount.add(PRICE_PER_DAY.multiply(BigDecimal.valueOf(days - MAX_DAYS_FOR_DEFAULT_PRICE)));
        }
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Calculates the frequent rental points based on the rental duration.
     *
     * @param days The number of days the movie is rented.
     * @return The frequent rental points earned.
     */
    @Override
    public int generatePoints(int days) {
        return RentalStrategy.super.generatePoints(days);
    }
}
