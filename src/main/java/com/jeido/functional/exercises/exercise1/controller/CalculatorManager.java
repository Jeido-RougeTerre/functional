package com.jeido.functional.exercises.exercise1.controller;

import java.util.HashMap;
import java.util.Map;

public class CalculatorManager {
    private static CalculatorManager instance;
    private final Map<String, Calculator<Double>> operators;

    public static CalculatorManager getInstance() {
        if (instance == null) {
            instance = new CalculatorManager();
        }
        return instance;
    }

    private CalculatorManager() {
        operators = new HashMap<>();
        operators.put("+", (x, y) -> x + y);
        operators.put("-", (x, y) -> x - y);
        operators.put("*", (x, y) -> x * y);
        operators.put("/", (x, y) -> x / y);
    }

    public Double process(String input) {
        if (input == null || input.isEmpty()) return null;

        for (String operator : operators.keySet()) {
            if (input.contains(operator)) {
                String[] split = input.split((operator.equals("+")) ? "\\+" : (operator.equals("*")) ? "\\*" : operator);
                String left = split[0];
                String right = split[1];
                try {
                    double dLeft = Double.parseDouble(left);
                    double dRight = Double.parseDouble(right);
                    return operators.get(operator).result(dLeft, dRight);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return null;
    }

}
