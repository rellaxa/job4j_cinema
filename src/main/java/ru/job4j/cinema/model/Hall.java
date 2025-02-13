package ru.job4j.cinema.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

@Setter
@Getter
public class Hall {

    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "name", "name",
            "row_count", "rowCount",
            "place_count", "placeCount",
            "description", "description"
    );

    private int id;

    private String name;

    private int rowCount;

    private int placeCount;

    private String description;

    public Hall() {
    }

    public Hall(String name, int rowCount, int placeCount, String description) {
        this.name = name;
        this.rowCount = rowCount;
        this.placeCount = placeCount;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hall hall = (Hall) o;
        return id == hall.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
