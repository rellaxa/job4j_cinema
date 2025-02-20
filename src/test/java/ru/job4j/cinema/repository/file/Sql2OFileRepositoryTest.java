package ru.job4j.cinema.repository.file;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;
import ru.job4j.cinema.configuration.DataSourceConfiguration;

import ru.job4j.cinema.model.File;

import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

class Sql2OFileRepositoryTest {

    private static Sql2OFileRepository sql2oFileRepository;

    @BeforeAll
    public static void initRepository() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2OFileRepositoryTest.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DataSourceConfiguration();
        var dataSource = configuration.connectionPool(url, username, password);
        var sql2o = new Sql2o(dataSource);

        sql2oFileRepository = new Sql2OFileRepository(sql2o);
    }

    @Test
    public void whenFindByIdThenGetCorrectFile() {
        var file = new File(1, "The Fall Guy", "files\\The Fall Guy.jpg");
        var fileOptional = sql2oFileRepository.findById(1);
        assertThat(fileOptional.get()).usingRecursiveComparison().isEqualTo(file);
    }

    @Test
    public void whenFindAllFiles() {
        var files = sql2oFileRepository.getFiles();
        assertThat(files.size()).isEqualTo(8);
    }
}