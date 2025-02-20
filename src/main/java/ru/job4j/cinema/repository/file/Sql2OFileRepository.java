package ru.job4j.cinema.repository.file;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.File;

import java.util.Collection;
import java.util.Optional;

@Repository
public class Sql2OFileRepository implements FileRepository {

    private final Sql2o sql2o;

    public Sql2OFileRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<File> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM files WHERE id = :id");
            var file = query.addParameter("id", id).executeAndFetchFirst(File.class);
            return Optional.ofNullable(file);
        }
    }

    @Override
    public Collection<File> getFiles() {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery("SELECT * FROM files").executeAndFetch(File.class);
        }
    }
}
