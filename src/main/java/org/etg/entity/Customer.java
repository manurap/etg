package org.etg.entity;

import java.util.List;

/**
 *
 * @param name the customer name
 * @param rentals the list of rentals
 */
public record Customer(String name, List<MovieRental> rentals) {
}
