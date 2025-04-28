package org.etg.entity;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentalInfoTest {
    private final RentalInfo rentalInfo = new RentalInfo();

    @Test
    void testStatementGeneration() {
        Customer customer = new Customer("Test A", List.of(
                new MovieRental("F001", 3),  // Regular movie for 3 days
                new MovieRental("F002", 1),  // Regular movie for 1 day
                new MovieRental("F004", 4)   // New release movie for 4 days
        ));

        String expectedStatement = """
            Rental Record for Test A
            \tYou've Got Mail\t3.50
            \tMatrix\t2.00
            \tFast & Furious X\t12.00
            Amount owed is 17.50
            You earned 4 frequent points
            """;

        assertEquals(expectedStatement, rentalInfo.statement(customer));
    }
}
