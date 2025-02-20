package ru.job4j.cinema.repository.ticket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import ru.job4j.cinema.model.Ticket;

import java.util.Optional;

@Slf4j
@Repository
public class Sql2oTicketRepository implements TicketRepository {

    private final Sql2o sql2o;

    public Sql2oTicketRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<Ticket> save(Ticket ticket) {
        try (Connection connection = sql2o.open()) {
            var sql = """
                    INSERT INTO tickets(session_id, row_number, place_number, user_id)
                    VALUES (:sessionId, :rowNumber, :placeNumber, :userId)
                    """;
            var query = connection.createQuery(sql, true)
                    .addParameter("sessionId", ticket.getSessionId())
                    .addParameter("rowNumber", ticket.getRowNumber())
                    .addParameter("placeNumber", ticket.getPlaceNumber())
                    .addParameter("userId", ticket.getUserId());
            int idGenerated = query.executeUpdate().getKey(Integer.class);
            ticket.setId(idGenerated);
            return Optional.of(ticket);
        } catch (Sql2oException e) {
            log.error("ticket: {} already exists", ticket);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Ticket> getTicketBySessionRowNumberAndPlaceNumber(int sessionId, int rowNumber, int placeNumber) {
        try (Connection connection = sql2o.open()) {
            var sql = """
                    SELECT * FROM tickets WHERE session_id = :sessionId AND row_number = :rowNumber AND place_number = :placeNumber
                    """;
            var query = connection.createQuery(sql)
                    .addParameter("sessionId", sessionId)
                    .addParameter("rowNumber", rowNumber)
                    .addParameter("placeNumber", placeNumber);
            var ticket = query.setColumnMappings(Ticket.COLUMN_MAPPING).executeAndFetchFirst(Ticket.class);
            return Optional.ofNullable(ticket);
        }
    }
}
