package com.jeido.functional.ui;

import com.jeido.functional.exercises.ExerciseBase;
import com.jeido.functional.exercises.ExerciseManager;

import java.util.Scanner;

public class ExerciseLogger {
    private static ExerciseLogger instance;
    private final ExerciseManager exerciseManager;
    private final Scanner scanner;

    private ExerciseLogger() {
        exerciseManager = ExerciseManager.getInstance();
        scanner = new Scanner(System.in);
    }

    public static ExerciseLogger getInstance() {
        if (instance == null) {
            instance = new ExerciseLogger();
        }
        return instance;
    }

    public void menu() {
        if (exerciseManager.getExercises().isEmpty()) {
            System.out.println("No exercises found");
            return;
        }

        while (true) {
            StringBuilder sb = new StringBuilder();
            sb.append("== Choose an Exercise ==\n");
            for (ExerciseBase ex : exerciseManager.getExercises()) {

                sb.append(ex).append("\n");
            }
            sb.append("0. Quit\n> ");
            System.out.print(sb);

            String input = scanner.nextLine();
            try {
                int choice = Integer.parseInt(input);
                if (choice == 0) {
                    scanner.close();
                    System.out.println("Goodbye");
                    return;
                }

                if (choice < 1 || choice > exerciseManager.getExercises().size()) {
                    System.out.println("Invalid exercise number [1-" + exerciseManager.getExercises().size() + "]");
                } else {

                    exerciseManager.start(choice - 1);
                    System.out.print("End of Exercise " + choice + " (Press RETURN to continue)");
                    String _a = scanner.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid exercise number");
            }
        }
    }

}
