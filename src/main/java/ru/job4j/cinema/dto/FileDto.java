package ru.job4j.cinema.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDto {

    private String name;

    private byte[] content;

    public FileDto(String name, byte[] content) {
        this.name = name;
        this.content = content;
    }

}
