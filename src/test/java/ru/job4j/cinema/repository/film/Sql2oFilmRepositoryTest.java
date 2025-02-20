package ru.job4j.cinema.repository.film;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;
import ru.job4j.cinema.configuration.DataSourceConfiguration;
import ru.job4j.cinema.model.Film;

import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

class Sql2oFilmRepositoryTest {

    private static Sql2oFilmRepository sql2oFilmRepository;

    @BeforeAll
    public static void initRepository() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oFilmRepositoryTest.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DataSourceConfiguration();
        var dataSource = configuration.connectionPool(url, username, password);
        var sql2o = new Sql2o(dataSource);

        sql2oFilmRepository = new Sql2oFilmRepository(sql2o);
    }

    @Test
    public void whenFindByIdThenGetCorrectFilm() {
        var film = Film.builder()
                .id(1)
                .name("The Fall Guy")

                .description("A down-on-his-luck stuntman finds himself thrust into a high-octane world of danger and humor as\n"
                        + "    he takes on risky jobs to save his career. This 2024 comedy reinvents the classic tale with thrilling stunts and quirky characters.")
                .year(2024)
                .genreId(3)
                .minimalAge(12)
                .durationInMinutes(110)
                .fileId(1)
                .build();
        var fileOptional = sql2oFilmRepository.findById(1);
        assertThat(fileOptional.get()).usingRecursiveComparison().isEqualTo(film);
    }

    @Test
    public void whenFindAll() {
        var collection = sql2oFilmRepository.getFilms();
        var list = collection.stream().toList();
        var filmNameAtLastIndex = list.get(list.size() - 1).getName();
        assertThat(collection.size()).isEqualTo(8);
        assertThat(filmNameAtLastIndex).isEqualTo("Nosferatu");
    }
}