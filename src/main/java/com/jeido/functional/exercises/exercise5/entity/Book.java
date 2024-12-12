package com.jeido.functional.exercises.exercise5.entity;

import java.time.LocalDate;

public record Book(String title, String author, String genre, LocalDate publicationDate, int pages, boolean available, double price) {
    @Override
    public String toString() {
        return "'" + title + '\'' +
                " by " + author + ", " + genre +
                " (" + publicationDate + ") " +
                pages + " pages " +
                ((available) ? "available" : "unavailable") +
                String.format(", %.2f$", price);
    }

    public static Book parse(String line) {
        String[] parts = line.split(",");
        String title = parts[0];
        String author = parts[1];
        String genre = parts[2];
        try {
            LocalDate publicationDate = LocalDate.parse(parts[3]);
            int pages = Integer.parseInt(parts[4]);
            boolean available = Boolean.parseBoolean(parts[5]);
            double price = Double.parseDouble(parts[6]);
            return new Book(title, author, genre, publicationDate, pages, available, price);
        } catch (Exception e) {
            return null;
        }
    }
}
