package com.jeido.functional.exercises.exercise4;

import com.jeido.functional.exercises.exercise4.entity.Command;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Exercise4 {

    private static final List<Command> COMMANDS = Arrays.asList(
            new Command(1, "Alice", Arrays.asList("Ordinateur", "Souris"), 1200.50, true),
            new Command(2, "Bob", Arrays.asList("Clavier", "Écran"), 300.75, false),
            new Command(3, "Charlie", Arrays.asList("Imprimante"), 150.00, true),
            new Command(4, "Alice", Arrays.asList("USB", "Casque"), 75.50, false),
            new Command(5, "Bob", Arrays.asList("Tablette"), 200.00, true)
    );

    public static void main(String[] args) {
        if (args.length > 0) {
            switch (args[0]) {
                case "1" -> displayAll();
                case "2" -> displayAllClient();
                case "3" -> displayAllTotal();
                case "4" -> displayArticlesAndPrice();
                case "5" -> displayDeliveredCommands();
                case "6" -> displayTotalPriceByClient();
                case "7" -> displayUniqueArticles();
                case "8" -> displayIsAllClientHaveAtLeastOneDeliveredCommand();
                default -> {
                    displayAll();
                    displayAllClient();
                    displayAllTotal();
                    displayArticlesAndPrice();
                    displayDeliveredCommands();
                    displayTotalPriceByClient();
                    displayUniqueArticles();
                    displayIsAllClientHaveAtLeastOneDeliveredCommand();
                }
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
                    0. Quit
                    >\s""");
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> displayAll();
                case "2" -> displayAllClient();
                case "3" -> displayAllTotal();
                case "4" -> displayArticlesAndPrice();
                case "5" -> displayDeliveredCommands();
                case "6" -> displayTotalPriceByClient();
                case "7" -> displayUniqueArticles();
                case "8" -> displayIsAllClientHaveAtLeastOneDeliveredCommand();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private static void displayAll() {
        COMMANDS.forEach(System.out::println);
    }

    private static void displayAllClient() {
        System.out.println("All Clients :");
        COMMANDS.stream().map(Command::getClient).distinct().forEach(System.out::println);
    }

    private static void displayAllTotal() {
        System.out.printf("All Total command price : %.2f$%n", COMMANDS.stream().mapToDouble(Command::getTotalPrice).sum());
    }

    private static void displayArticlesAndPrice() {
        COMMANDS.forEach(c -> System.out.printf("#%d%s - %.2f%n", c.getId(), c.getArticles(), c.getTotalPrice()));
    }

    private static void displayDeliveredCommands() {
        COMMANDS.stream().filter(Command::isDelivered).forEach(System.out::println);
    }

    private static void displayTotalPriceByClient() {
        COMMANDS.stream().collect(
                Collectors.groupingBy(Command::getClient, Collectors.summingDouble(Command::getTotalPrice))
        ).forEach((client, price) -> System.out.printf("'%s' : %.2f%n", client, price));
    }

    private static void displayUniqueArticles() {
        COMMANDS.stream().map(Command::getArticles).distinct().forEach(System.out::println);
    }

    private static void displayIsAllClientHaveAtLeastOneDeliveredCommand() {
        System.out.printf("Is all client have at least one deliverd command : %s%n",
                COMMANDS.stream().collect(Collectors.groupingBy(Command::getClient)).values().stream()
                        .allMatch(v -> v.stream().anyMatch(Command::isDelivered))?
                "yes" : "no"
        );
    }
}
