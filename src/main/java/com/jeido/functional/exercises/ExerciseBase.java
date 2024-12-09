package com.jeido.functional.exercises;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public record ExerciseBase(int id, String title, Method method) {
    @Override
    public String toString() {
        return id + ". " + title;
    }

    public void start() {
        try {
            method.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }


    }
}
