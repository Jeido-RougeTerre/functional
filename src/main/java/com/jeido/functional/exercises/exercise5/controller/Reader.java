package com.jeido.functional.exercises.exercise5.controller;


import com.jeido.functional.exercises.exercise5.entity.Book;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private static Reader instance = null;
    private Reader() {}
    public static Reader getInstance() {
        if (instance == null) {
            instance = new Reader();
        }
        return instance;
    }

    public List<Book> read() {
        List<Book> books = new ArrayList<>();
        try {
            File file = new File("src/main/resources/exercises/exercise5/books_dataset.csv".replace("/", File.separator));
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while (reader.ready()) {
                String line = reader.readLine();
                Book book = Book.parse(line);
                if (book != null) {
                    books.add(book);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }
}
