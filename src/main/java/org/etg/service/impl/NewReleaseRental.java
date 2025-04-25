package org.etg.service.impl;

import org.etg.service.RentalStrategy;

/**
 * Rental strategy for NewRelease's movies.
 */
public class NewReleaseRental implements RentalStrategy {
    private static final int PRICE_PER_DAY = 3;
    private static final int MIN_DAYS_FOR_EXTRA_POINT = 2;

    @Override
    public double calculatePrice(int days) {
        return days * PRICE_PER_DAY;
    }

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
