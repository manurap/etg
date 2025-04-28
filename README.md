# Movie Rental Application

The Movie Rental Application is a Java-based system that allows users to rent movies and generate rental summaries for customers. 

## Prerequisites
    JDK 17 installed on your machine.
    Maven

## Refactoring
- Maven Integration
- Add Junit Libary to write unit test
- Add Unit test
- Java Records: Compact and immutable data structures for Movie, MovieRental, and Customer.
- Used MovieType Enum
- Applied Strategy Pattern – Rental calculations are encapsulated into separate strategy classes, avoiding if-else checks and Ensures easy extensibility for new rental categories.
- Stream API for Processing – Eliminated loops, making the logic more declarative and concise.
- Encapsulated Rental Logic: Moved calculation logic to RentalCalculationService for better separation of concerns.
- MovieService can manage movie-related operations (in future - fetching from a database, filtering, caching)
- MovieService apply Singleton Design pattern to ensures a single instance – Prevents unnecessary object creation.
- Add Factory pattern to create a dynamically rental startgey based on Movie Type
- Uses BigDecimal for precise calculations, avoiding floating-point rounding issues.
- SOLID Principles -Single Responsibility Principle (SRP), Open/Closed Principle (OCP), Interface Segregation Principle (ISP) 

🔹 Avoids tightly coupled code, reducing the risk of breaking functionality.
🔹 Encourages modular architecture, making it easier to modify specific parts.
🔹 Improves testability, ensuring unit tests are effective without unnecessary 


## To run the test:

```
javac src/*.java
java -cp src Main
```
