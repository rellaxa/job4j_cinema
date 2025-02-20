package ru.job4j.cinema.dto;

import lombok.Getter;
import lombok.Setter;
import ru.job4j.cinema.model.Hall;

import java.time.LocalDateTime;

@Setter
@Getter
public class FilmSessionDto {

    private int id;

    private String filmName;

    private Hall hall;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int fileId;

    private int price;

    public FilmSessionDto(int id, LocalDateTime startTime, LocalDateTime endTime, int price) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    @Override
    public String toString() {
        return "FilmSessionDto{"
                + "id=" + id
                + ", filmName='" + filmName + '\''
                + ", hall=" + hall
                + ", startTime=" + startTime
                + ", endTime=" + endTime
                + ", fileId=" + fileId
                + ", price=" + price
                + '}';
    }
}
