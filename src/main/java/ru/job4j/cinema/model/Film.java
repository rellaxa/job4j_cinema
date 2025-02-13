package ru.job4j.cinema.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

@Setter
@Getter
public class Film {

    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "name", "name",
            "description", "description",
            "year", "year",
            "genre_id", "genreId",
            "minimal_age", "minimalAge",
            "duration_in_minutes", "durationInMinutes",
            "file_id", "fileId"
    );

    private int id;

    private String name;

    private String description;

    private int year;

    private int genreId;

    private int minimalAge;

    private int durationInMinutes;

    private int fileId;

    public Film() {
    }

    public Film(String name, String description, int year, int genreId, int minimalAge, int durationInMinutes, int fileId) {
        this.name = name;
        this.description = description;
        this.year = year;
        this.genreId = genreId;
        this.minimalAge = minimalAge;
        this.durationInMinutes = durationInMinutes;
        this.fileId = fileId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Film film = (Film) o;
        return id == film.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
