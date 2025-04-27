package org.etg.entity;

import org.etg.service.impl.RentalCalculationService;

import java.util.Map;
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
                    double amount = calculationService.calculateAmount(rental);
                    int points = calculationService.calculateFrequentPoints(rental);

                    return new Object() {
                        final double totalAmount = amount;
                        final int frequentPoints = points;
                        final String details = "\t" + movie.title() + "\t" + amount + "\n";
                    };
                })
                .toList();

        // Calculate totals
        double totalAmount = rentalDetails.stream().mapToDouble(r -> r.totalAmount).sum();
        int frequentEnterPoints = rentalDetails.stream().mapToInt(r -> r.frequentPoints).sum();
        String details = rentalDetails.stream().map(r -> r.details).collect(Collectors.joining());

        return "Rental Record for " + customer.name() + "\n" +
                details +
                "Amount owed is " + totalAmount + "\n" +
                "You earned " + frequentEnterPoints + " frequent points\n";
    }
}
