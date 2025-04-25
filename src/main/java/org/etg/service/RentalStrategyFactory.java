package org.etg.service;

import org.etg.entity.MovieType;
import org.etg.service.impl.ChildrenRental;
import org.etg.service.impl.NewReleaseRental;
import org.etg.service.impl.RegularRental;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory class for creating rental strategies based on movie type.
 */
public class RentalStrategyFactory {
    private static final Map<MovieType,RentalStrategy> strategyMap = new HashMap<>();

    static {
        strategyMap.put(MovieType.REGULAR, new RegularRental());
        strategyMap.put(MovieType.NEW_RELEASE, new NewReleaseRental());
        strategyMap.put(MovieType.CHILDREN, new ChildrenRental());
    }

    public static RentalStrategy getStrategy(MovieType type) {
        return strategyMap.get(type);
    }
}
