package ru.job4j.cinema.repository.session;

import ru.job4j.cinema.model.FilmSession;

import java.util.Collection;
import java.util.Optional;

public interface FilmSessionRepository {

    Optional<FilmSession> getById(int id);

    Collection<FilmSession> getFilmSessions();
}
