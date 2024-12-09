package com.jeido.functional.exercises.exercise1.ui;

import com.jeido.functional.exercises.exercise1.controller.CalculatorManager;

import java.util.Scanner;
import java.util.function.Consumer;

public class CalculatorUI {
    private static CalculatorUI instance;
    private final CalculatorManager manager = CalculatorManager.getInstance();
    private final Scanner scanner = new Scanner(System.in);
    private Consumer<String> output = System.out::print;

    public static CalculatorUI getInstance() {
        if (instance == null) {
            instance = new CalculatorUI();
        }
        return instance;
    }

    private CalculatorUI() {}

    public void display() {
        while (true) {
            output.accept("""
                    == Calculator ==
                    Enter a simple operation like this : 14 + 5
                    to quit enter 'stop'
                    >\s""");
            String operation = scanner.nextLine();
            if (operation.equals("stop")) {
                return;
            }

            Double result = manager.process(operation);

            if (result != null) {
                output.accept(operation + " = " + result + '\n');
            } else {
                output.accept(operation + " is invalid" + '\n');
            }
        }
    }
}
