package ru.job4j.cinema.repository.session;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.FilmSession;

import java.util.Collection;
import java.util.Optional;

@Repository
public class Sql2oFilmSessionRepository implements FilmSessionRepository {

    private final Sql2o sql2o;

    public Sql2oFilmSessionRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<FilmSession> getById(int id) {
        try (Connection con = sql2o.open()) {
            var query = con.createQuery("SELECT * FROM film_sessions WHERE id = :id")
                    .addParameter("id", id);
            return Optional.ofNullable(query.setColumnMappings(FilmSession.COLUMN_MAPPING).executeAndFetchFirst(FilmSession.class));
        }
    }

    @Override
    public Collection<FilmSession> getFilmSessions() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM film_sessions")
                    .setColumnMappings(FilmSession.COLUMN_MAPPING)
                    .executeAndFetch(FilmSession.class);
        }
    }
}
