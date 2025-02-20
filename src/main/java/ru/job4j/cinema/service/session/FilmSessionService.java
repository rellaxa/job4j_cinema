package ru.job4j.cinema.service.session;

import ru.job4j.cinema.dto.FilmSessionDto;

import java.util.Collection;
import java.util.Optional;

public interface FilmSessionService {

    Collection<FilmSessionDto> getSessions();

    Optional<FilmSessionDto> getSessionById(int sessionId);
}
