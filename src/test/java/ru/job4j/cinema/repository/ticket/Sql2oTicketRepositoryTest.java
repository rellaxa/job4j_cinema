package ru.job4j.cinema.repository.ticket;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;
import ru.job4j.cinema.configuration.DataSourceConfiguration;
import ru.job4j.cinema.model.Ticket;

import java.util.Optional;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

class Sql2oTicketRepositoryTest {

    private static TicketRepository sql2oTicketRepository;

    private static Sql2o sql2o;

    @BeforeAll
    public static void initRepository() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oTicketRepositoryTest.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DataSourceConfiguration();
        var dataSource = configuration.connectionPool(url, username, password);
        sql2o = new Sql2o(dataSource);

        sql2oTicketRepository = new Sql2oTicketRepository(sql2o);
    }

    @AfterEach
    public void deleteFile() {
        try (var connection = sql2o.open()) {
            connection.createQuery("DELETE FROM tickets").executeUpdate();
        }
    }

    @Test
    public void whenBuyTheTicketThenTicketIsSaved() {
        var ticket = new Ticket(1, 2, 3, 4);
        var savedTicket = sql2oTicketRepository.save(ticket).get();
        assertThat(ticket).usingRecursiveComparison().isEqualTo(savedTicket);
    }

    @Test
    public void whenTheSameTicketThenTicketIsNotSaved() {
        var ticket = new Ticket(1, 2, 3, 4);
        sql2oTicketRepository.save(ticket);
        var savedTicket = sql2oTicketRepository.save(ticket);
        assertThat(savedTicket).isEqualTo(Optional.empty());
    }

    @Test
    public void whenSaveTicketAndFindTheSameTicketByRowSessionAndPlaceNumber() {
        var ticket = new Ticket(1, 2, 3, 4);
        sql2oTicketRepository.save(ticket);
        var foundTicket = sql2oTicketRepository.getTicketBySessionRowNumberAndPlaceNumber(
                1, 2, 3).get();
        assertThat(foundTicket).isEqualTo(ticket);
    }
}