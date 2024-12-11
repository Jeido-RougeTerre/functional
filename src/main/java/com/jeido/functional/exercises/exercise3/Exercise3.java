package com.jeido.functional.exercises.exercise3;

import com.jeido.functional.exercises.exercise3.controller.Reader;
import com.jeido.functional.exercises.exercise3.entity.Film;
import com.jeido.functional.exercises.exercise3.utils.DisplayFilms;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Exercise3 {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Reader reader = Reader.getInstance();
        List<Film> films = reader.read();

        if (args.length > 0) {
            switch (args[0]) {
                case "1" -> one(films);
                case "2" -> two(films);
                case "3" -> three(films);
                case "4" -> four(films);
                case "5" -> five(films);
                case "6" -> six(films);
                case "7" -> seven(films);
                case "full" -> {
                    one(films);
                    two(films);
                    three(films);
                    four(films);
                    five(films);
                    six(films);
                    seven(films);
                }
                default -> menu(films);
            }
        } else {
           menu(films);
        }
    }

    private static void menu(List<Film> films) {
        while (true) {
            System.out.print("""
                        == Choose a part ==
                        1. Read and display streams
                        2. Stream filters
                        3. Filter and limits
                        4. Grouping
                        5. Operation
                        6. Transform
                        7. Advanced Question
                        0. Quit
                        >\s""");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> one(films);
                case "2" -> two(films);
                case "3" -> three(films);
                case "4" -> four(films);
                case "5" -> five(films);
                case "6" -> six(films);
                case "7" -> seven(films);
                case "full" -> {
                    one(films);
                    two(films);
                    three(films);
                    four(films);
                    five(films);
                    six(films);
                    seven(films);
                }
                case "0" -> {
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void one(List<Film> films) {
        System.out.println("1 :");
        System.out.println("\t1.1 :");
        DisplayFilms.display(films.stream().limit(10).toList());

        System.out.println("\t1.2 : " + films.stream().map(Film::title).toList());
    }

    private static void two(List<Film> films) {
        System.out.println("2 :");
        System.out.println("\t2.1 : " + films.stream().filter(f -> f.genre().equalsIgnoreCase("action")).map(Film::title).toList());

        System.out.println("\t2.2 : " + films.stream().filter(f -> f.date().getYear() >= 2000).map(Film::title).toList());

        System.out.println("\t2.3 : ");
        DisplayFilms.display(films.stream().filter(f -> f.director().equalsIgnoreCase("michael webster")).toList());
    }

    private static void three(List<Film> films) {
        System.out.println("3 :");
        System.out.println("\t3.1 :");
        DisplayFilms.display(films.stream().sorted(Comparator.comparingInt(Film::nbEntry).reversed()).limit(5).toList());

        System.out.println("\t3.2 : " + films.stream().sorted(Comparator.comparing(Film::date).reversed()).map(Film::title).toList());

        System.out.println("\t3.3 :");
        DisplayFilms.display(films.stream().sorted(Comparator.comparingInt(Film::nbEntry)).limit(10).toList());

    }

    private static void four(List<Film> films) {
        System.out.println("4 :");
        System.out.println("\t4.1 :");
        films.stream().collect(Collectors.groupingBy(Film::genre, Collectors.counting()))
                .forEach((entry, count) -> System.out.println("\t\t" + entry + " : " + count));

        System.out.println("\t4.2 :");
        films.stream().collect(Collectors.groupingBy(Film::director)).forEach((entry, dirFilms) -> System.out.println("\t\t" + entry + " : " + dirFilms.stream().map(Film::title).toList()));
    }

    private static void five(List<Film> films) {
        System.out.println("5");
        System.out.println("\t5.1 : " + films.stream().mapToLong(Film::nbEntry).sum());

        System.out.println("\t5.2 : " + films.stream()
                .collect(Collectors.groupingBy(Film::genre, Collectors.summingLong(Film::nbEntry)))
                .entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null));

        System.out.println("\t5.3 : dir : michael webster (" + films.stream().filter(
                f -> f.director().equalsIgnoreCase("michael webster")
        ).mapToDouble(Film::nbEntry).average().orElse(0.0) + ")");
    }

    private static void six(List<Film> films) {
        System.out.println("6");
        System.out.println("\t6.1 :");
        films.stream().forEach(film -> System.out.printf("\t\t%s (%s) - By %s in %d%n", film.title(), film.genre(), film.director(), film.date().getYear()));

        System.out.println("\t6.2 : " + films.stream().collect(Collectors.groupingBy(Film::genre)).keySet());
    }

    private static void seven(List<Film> films) {
        System.out.println("7");
        System.out.println("\t7.1 :");
        films.stream().collect(Collectors.groupingBy(Film::genre)).forEach((genre, genFilms) -> System.out.println("\t\t" + genre + " : " + genFilms.stream()
                .sorted(Comparator.comparing(Film::date).reversed()).limit(1).toList()));

        System.out.println("\t7.2 : " + films.stream().collect(Collectors.groupingBy(Film::director)).entrySet()
                .stream().filter(m -> m.getValue().size() > 50)
                .collect(Collectors.groupingBy(Map.Entry::getKey)).keySet());

        System.out.println("\t7.3 : " + films.stream().filter(f -> f.date().getYear() <= 2000).filter(f -> f.date().getYear() >= 1900).mapToLong(Film::nbEntry).sum());
    }
}
