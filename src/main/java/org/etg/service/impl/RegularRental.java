package org.etg.service.impl;

import org.etg.service.RentalStrategy;

/**
 * Rental strategy for regular's movies.
 */
public class RegularRental implements RentalStrategy {
    private static final double DEFAULT_PRICE = 2;
    private static final double PRICE_PER_DAY = 1.5;
    private static final int MAX_DAYS_FOR_DEFAULT_PRICE = 2;

    @Override
    public double calculatePrice(int days) {
        if (days <= 0) {
            return 0;
        }

        double amount = DEFAULT_PRICE;
        if (days > MAX_DAYS_FOR_DEFAULT_PRICE) {
            amount += (days - MAX_DAYS_FOR_DEFAULT_PRICE) * PRICE_PER_DAY;
        }

        return amount;
    }

    @Override
    public int generatePoints(int days) {
        return RentalStrategy.super.generatePoints(days);
    }
}
