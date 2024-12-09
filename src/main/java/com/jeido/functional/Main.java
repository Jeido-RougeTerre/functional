package com.jeido.functional;

import com.jeido.functional.ui.ExerciseLogger;

public class Main {
    public static void main(String[] args) {
        ExerciseLogger el = ExerciseLogger.getInstance();
        el.menu();
    }
}
