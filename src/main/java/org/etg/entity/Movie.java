package org.etg.entity;

import org.etg.service.RentalStrategy;
import org.etg.service.RentalStrategyFactory;

/**
 * @param title
 * @param movieType
 */
public record Movie(String title,MovieType movieType) {
    public RentalStrategy getStrategy() {
        return RentalStrategyFactory.getStrategy(movieType);
    }

}
