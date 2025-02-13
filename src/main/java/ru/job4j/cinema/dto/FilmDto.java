package ru.job4j.cinema.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FilmDto {

    private int id;

    private String name;

    private String description;

    private int year;

    private int minimalAge;

    private int durationInMinutes;

    private int fileId;

    private String genre;

    public FilmDto() {
    }

    public FilmDto(int id, String name, String description, int year, int minimalAge, int durationInMinutes, int fileId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.minimalAge = minimalAge;
        this.durationInMinutes = durationInMinutes;
        this.fileId = fileId;
    }
}
