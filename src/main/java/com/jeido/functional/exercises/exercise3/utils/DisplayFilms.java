package com.jeido.functional.exercises.exercise3.utils;

import com.jeido.functional.exercises.exercise3.entity.Film;

import java.util.List;

import static com.jeido.functional.utils.StringUtils.*;

public class DisplayFilms {

    public static void display(List<Film> films) {
        String titleHead = "Title";
        String dateHead = "Release Date";
        String directorHead = "Director";
        String genreHead = "Genre";
        String nbEntryHead = "Nb. Entries";
        int titleSize = Integer.max(films.stream().map(Film::title).mapToInt(String::length).max().orElse(titleHead.length()), titleHead.length());
        int dateSize = dateHead.length();
        int directorSize = Integer.max(films.stream().map(Film::director).mapToInt(String::length).max().orElse(directorHead.length()), directorHead.length());
        int genreSize = Integer.max(films.stream().map(Film::genre).mapToInt(String::length).max().orElse(genreHead.length()), genreHead.length());
        int nbEntrySize = Integer.max(films.stream().map(f -> "" + f.nbEntry()).mapToInt(String::length).max().orElse(nbEntryHead.length()), nbEntryHead.length());
        StringBuilder sb = new StringBuilder();
        sb.append(center(titleHead, titleSize)).append(" | ").append(center(dateHead, dateSize)).append(" | ");
        sb.append(center(directorHead, directorSize)).append(" | ").append(center(genreHead, genreSize)).append(" | ");
        sb.append(center(nbEntryHead, nbEntrySize)).append('\n');
        for (Film f : films) {
            sb.append(left(f.title(), titleSize)).append(" | ");
            sb.append(right(f.date().toString(), dateSize)).append(" | ");
            sb.append(left(f.director(), directorSize)).append(" | ").append(left(f.genre(), genreSize)).append(" | ");
            sb.append(right(f.nbEntry() + "", nbEntrySize)).append('\n');
        }

        System.out.print(sb);
    }
}
