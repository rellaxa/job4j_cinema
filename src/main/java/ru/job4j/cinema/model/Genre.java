package ru.job4j.cinema.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class Genre {

    private int id;

    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Genre genre = (Genre) o;
        return id == genre.id && Objects.equals(name, genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
