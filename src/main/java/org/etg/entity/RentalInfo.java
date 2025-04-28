package org.etg.entity;

import org.etg.service.impl.RentalCalculationService;

import java.math.BigDecimal;
import java.util.stream.Collectors;

/**
 * processing moving rental information
 */
public class RentalInfo {
    private final RentalCalculationService calculationService = new RentalCalculationService();

    public String statement(Customer customer) {
        var rentalDetails = customer.rentals().stream()
                .map(rental -> {
                    Movie movie = calculationService.getMovie(rental);
                    BigDecimal amount = calculationService.calculateAmount(rental);
                    int points = calculationService.calculateFrequentPoints(rental);

                    return new RentalDetail("\t" + movie.title() + "\t" + amount + "\n", amount, points);
                })
                .toList();

        // Calculate totals using BigDecimal
        BigDecimal totalAmount = rentalDetails.stream()
                .map(RentalDetail::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int frequentEnterPoints = rentalDetails.stream()
                .mapToInt(RentalDetail::points)
                .sum();

        String details = rentalDetails.stream()
                .map(RentalDetail::details)
                .collect(Collectors.joining());

        return "Rental Record for " + customer.name() + "\n" +
                details +
                "Amount owed is " + totalAmount + "\n" +
                "You earned " + frequentEnterPoints + " frequent points\n";
    }
}
