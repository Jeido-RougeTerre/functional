package com.jeido.functional.exercises.exercise3.controller;

import com.jeido.functional.exercises.exercise3.entity.Film;

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

    public List<Film> read() {
        List<Film> films = new ArrayList<>();
        try {
            File file = new File("src/main/resources/exercises/exercise3/films_with_genres 1.csv".replace("/", File.separator));
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while (reader.ready()) {
                String line = reader.readLine();
                Film film = Film.parse(line);
                if (film != null) {
                    films.add(film);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return films;
    }
}
