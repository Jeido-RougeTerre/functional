package com.jeido.functional.exercises.exercise1.controller;

@FunctionalInterface
public interface Calculator<T extends Number> {
    T result(T a, T b);
}
