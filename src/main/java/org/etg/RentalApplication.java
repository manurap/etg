package org.etg;

import org.etg.entity.Customer;
import org.etg.entity.MovieRental;
import org.etg.entity.RentalInfo;

import java.util.Arrays;

public class RentalApplication {

    public static void main(String[] args) {
        String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.50\n\tMatrix\t2.00\nAmount owed is 5.50\nYou earned 2 frequent points\n";

        String result = new RentalInfo().statement(
                new Customer("C. U. Stomer",
                        Arrays.asList(
                                new MovieRental("F001", 3),
                                new MovieRental("F002", 1))));

        if (!result.equals(expected)) {
            throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
        }

        System.out.println("Success");
    }
}
