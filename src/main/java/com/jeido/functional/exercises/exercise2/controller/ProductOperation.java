package com.jeido.functional.exercises.exercise2.controller;

import com.jeido.functional.exercises.exercise2.entity.Product;

@FunctionalInterface
public interface ProductOperation {
    Product operate(Product product);
}
