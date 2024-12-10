package com.jeido.functional.exercises.exercise3.entity;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public record Film(String title, LocalDate date, String director, String genre, int nbEntry) {

    @Override
    public String toString() {
        return title + ", " + date + ", " + director + ", " + genre + ", " + nbEntry;
    }

    public static Film parse(String string) {
        String[] split = string.split(",");
        String title = split[0];
        String dateString = split[1];
        String director = split[2];
        String genre = split[3];
        String nbEntryString = split[4];

        try {

            LocalDate date = LocalDate.parse(dateString);
            int nbEntry = Integer.parseInt(nbEntryString);

            return new Film(title, date, director, genre, nbEntry);
        } catch (NumberFormatException | DateTimeParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
