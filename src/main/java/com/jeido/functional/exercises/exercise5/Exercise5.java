package com.jeido.functional.exercises.exercise5;

import com.jeido.functional.exercises.exercise5.controller.Reader;
import com.jeido.functional.exercises.exercise5.entity.Book;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Exercise5 {
    private static final Reader READER = Reader.getInstance();
    private static final List<Book> BOOKS = READER.read();

    public static void main(String[] args) {
        if (args.length > 0) {
            switch (args[0]) {
                case "1" -> displayAvailableBooks();
                case "2" -> displayTitleAndAuthorForBooksOlderThan1950();
                case "3" -> displayBooksByGenre();
                case "4" -> displayOldestBook();
                case "5" -> displayIsBooksWithTitleStartsWithHarry();
                case "6" -> displayAveragePricesOfAvailableBooks();
                case "7" -> displayBooksSortedByPagesAndByPrice();
                case "8" -> displayTotalPagesForFantasyBooks();
                case "9" -> displayAvailableMostExpensiveBook();
                case "10" -> displayMostProlificAuthors();
                case "11" -> displayNbBooksByGenre();
                case "12" -> {

                    if (args.length > 1) {
                        try {
                            double price = Double.parseDouble(args[1]);
                            displayAvailableBooksLowerThan(price);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid price");
                        }
                    }
                    System.out.println("Invalid price");
                }
                case "13" -> displayTotalNbPagesByYear();
                case "full" -> full();
                default -> menu();

            }
        } else {
            menu();
        }
    }

    private static void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("""
                    == Choose an action ==
                    1. Display all commands
                    2. Display all clients
                    3. Display all total
                    4. Display all articles
                    5. Display all delivered commands
                    6. Display total price per client
                    7. Display unique articles
                    8. Display is client have at least one delivered command
                    9. Most expansive available book
                    10. Most prolific authors
                    11. Count Books for genres
                    12. Find books available under a price
                    13. Total pages per year
                    0. Quit
                    >\s""");
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> displayAvailableBooks();
                case "2" -> displayTitleAndAuthorForBooksOlderThan1950();
                case "3" -> displayBooksByGenre();
                case "4" -> displayOldestBook();
                case "5" -> displayIsBooksWithTitleStartsWithHarry();
                case "6" -> displayAveragePricesOfAvailableBooks();
                case "7" -> displayBooksSortedByPagesAndByPrice();
                case "8" -> displayTotalPagesForFantasyBooks();
                case "9" -> displayAvailableMostExpensiveBook();
                case "10" -> displayMostProlificAuthors();
                case "11" -> displayNbBooksByGenre();
                case "12" -> {
                    System.out.print("enter a price : ");
                    String priceStr = scanner.nextLine();
                    try {
                        double price = Double.parseDouble(priceStr);
                        displayAvailableBooksLowerThan(price);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price");
                    }
                }
                case "13" -> displayTotalNbPagesByYear();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private static void displayAvailableBooks() {
        System.out.println("Available Books:");
        BOOKS.stream().filter(Book::available).forEach(System.out::println);
    }

    private static void displayTitleAndAuthorForBooksOlderThan1950() {
        System.out.println("Title and Author for Books older than 1950:");
        BOOKS.stream().filter(b -> b.publicationDate().getYear() < 1950)
                .collect(Collectors.groupingBy(Book::title))
                .forEach((title, books) -> books.forEach(
                        b -> System.out.printf("'%s' by %s%n", title, b.author())
                ));
    }

    private static void displayBooksByGenre() {
        System.out.println("Books by Genre:");
        BOOKS.stream().collect(Collectors.groupingBy(Book::genre)).forEach((genre, books) -> {
            StringBuilder sb = new StringBuilder(genre).append(" (").append(books.size()).append(") :\n");
            books.forEach(b -> sb.append('\t').append(b).append('\n'));
            System.out.print(sb);
        });
    }

    private static void displayOldestBook() {
        BOOKS.stream().min(Comparator.comparing(Book::publicationDate)).ifPresent(b -> System.out.printf("The oldest Book is '%s' published in %d%n", b.title(), b.publicationDate().getYear()));
    }

    private static void displayIsBooksWithTitleStartsWithHarry() {
        System.out.printf("Is there a book starting with 'Harry' ? : %s.%n",
                BOOKS.stream().anyMatch(b -> b.title().startsWith("Harry"))? "Yes" : "No"
        );
    }

    private static void displayAveragePricesOfAvailableBooks() {
        BOOKS.stream().filter(Book::available).mapToDouble(Book::price).average()
                .ifPresent( d -> System.out.printf("Average Prices of Available Books is %.2f$%n", d));
    }

    private static void displayBooksSortedByPagesAndByPrice() {
        System.out.println("Books sorted by Pages and Prices:");
        BOOKS.stream().sorted(Comparator.comparingInt(Book::pages).thenComparingDouble(Book::price)).forEach(System.out::println);
    }

    private static void displayTotalPagesForFantasyBooks() {
        System.out.printf("The total pages for Fantasy Books is %d.%n", BOOKS.stream()
                .filter(b -> b.genre().equalsIgnoreCase("Fantasy")).mapToInt(Book::pages).sum());
    }

    private static void displayAvailableMostExpensiveBook() {
        BOOKS.stream().filter(Book::available).max(Comparator.comparingDouble(Book::price))
                .ifPresent(b -> System.out.printf("The most expensive Book available is '%s' by %s for %.2f$.%n",
                        b.title(),
                        b.author(),
                        b.price()
                )
        );
    }

    private static void displayMostProlificAuthors() {
        BOOKS.stream().collect(Collectors.groupingBy(Book::author))
                .entrySet().stream().filter(entry -> entry.getValue().size() > 17 )
                .forEach(entry -> {
            StringBuilder sb = new StringBuilder(entry.getKey()).append(" (").append(entry.getValue().size());
            sb.append(" Books) : \n");
            entry.getValue().forEach(b -> sb.append('\t').append(b).append('\n'));
            System.out.println(sb);
        });
    }

    private static void displayNbBooksByGenre() {
        BOOKS.stream().collect(Collectors.groupingBy(Book::genre, Collectors.counting()))
                .forEach((genre, books) -> System.out.printf("%s %d Books.%n", genre, books));
    }

    private static void displayAvailableBooksLowerThan(double threshold) {
        System.out.printf("Available Books lower than %.2f$ :%n", threshold);
        BOOKS.stream().filter(b -> b.price() < threshold && b.available()).forEach(System.out::println);
    }

    private static void displayTotalNbPagesByYear() {
        BOOKS.stream().collect(Collectors.groupingBy(
                b -> b.publicationDate().getYear(), Collectors.summingInt(Book::pages)
                )).forEach((key, value) -> System.out.printf(
                        "%d %d pages.%n",
                        key, value)
                );
    }

    private static void full() {
        displayAvailableBooks();
        displayTitleAndAuthorForBooksOlderThan1950();
        displayBooksByGenre();
        displayOldestBook();
        displayIsBooksWithTitleStartsWithHarry();
        displayAveragePricesOfAvailableBooks();
        displayBooksSortedByPagesAndByPrice();
        displayTotalPagesForFantasyBooks();
        displayAvailableMostExpensiveBook();
        displayMostProlificAuthors();
        displayNbBooksByGenre();
        displayAvailableBooksLowerThan(25.0);
        displayTotalNbPagesByYear();

    }




}
