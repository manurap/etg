package org.etg.service;

import java.math.BigDecimal;

public interface RentalStrategy {

    /**
     * calculate rental
     * @param days number of rented days
     * @return price
     */
    BigDecimal calculatePrice(int days);

    /**
     * calculate points based on rented days
     * @param days
     * @return points
     */
    default int generatePoints(int days) {
        if (days <= 0) {
            return 0;
        }
        return 1;
    }
}
