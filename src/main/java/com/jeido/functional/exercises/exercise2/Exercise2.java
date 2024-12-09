package com.jeido.functional.exercises.exercise2;

import com.jeido.functional.exercises.exercise2.controller.ProductFilter;
import com.jeido.functional.exercises.exercise2.controller.ProductOperation;
import com.jeido.functional.exercises.exercise2.controller.ProductTransformer;
import com.jeido.functional.exercises.exercise2.entity.Product;

import java.util.List;

public class Exercise2 {
    public static void start() {
        Product p1 = new Product("Chocolate", 15.25, 2);
        Product p2 = new Product("Toilet Paper", 2.50, 0);
        Product p3 = new Product("Tentacles pickles", 14.50, 3);
        List<Product> products = List.of(p1, p2, p3);

        ProductFilter inStock = product -> product.getQuantity() > 0;
        ProductFilter namedChocolate = (product) -> product.getName().contains("Chocolate");
        ProductTransformer add10Percent = p -> {
            p.setPrice(p.getPrice() + p.getPrice() * .1);
            return p;
        };

        ProductTransformer rem10Percent = p -> {
            p.setPrice(p.getPrice() - p.getPrice() * .1);
            return p;
        };

        ProductTransformer restock5 = p -> {
            p.setQuantity(p.getQuantity() + 5);
            return p;
        };

        ProductTransformer unload = p -> {
            if (p.getQuantity() > 0) {
                p.setQuantity(p.getQuantity() - 1);
            }
            return p;
        };

        ProductOperation sale10percent = p -> {
            rem10Percent.transform(p).setName("SALE 10% " + p.getName());
            return p;
        };

        System.out.println("List of products: " + products);
        System.out.println("List of products in stock: " + products.stream().filter(inStock::filter).toList());
        System.out.println("List of products named 'Chocolate': " + products.stream().filter(namedChocolate::filter).toList());
        System.out.println("add10Percent: " + products.stream().map(add10Percent::transform).toList());
        System.out.println("rem10Percent: " + products.stream().map(rem10Percent::transform).toList());
        System.out.println("restock5: " + products.stream().map(restock5::transform).toList());
        System.out.println("unload: " + products.stream().map(unload::transform).toList());
        System.out.println("SALE 10%!!!!!! SASALELE : " + products.stream().map(sale10percent::operate).toList());
    }
}
