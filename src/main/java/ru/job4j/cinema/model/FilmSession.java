package ru.job4j.cinema.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Setter
@Getter
public class FilmSession {

    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "film_id", "filmId",
            "halls_id", "hallId",
            "start_time", "startTime",
            "end_time", "endTime",
            "price", "price"
    );

    private int id;

    private int filmId;

    private int hallId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int price;

    public FilmSession() {
    }

    public FilmSession(int filmId, int hallId, LocalDateTime startTime, LocalDateTime endTime, int price) {
        this.filmId = filmId;
        this.hallId = hallId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FilmSession that = (FilmSession) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
