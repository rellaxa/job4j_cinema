package ru.job4j.cinema.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

@Setter
@Getter
public class Ticket {

    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "session_id", "sessionId",
            "row_number", "rowNumber",
            "place_number", "placeNumber",
            "user_id", "userId"
    );

    private int id;

    private int sessionId;

    private int rowNumber;

    private int placeNumber;

    private int userId;

    public Ticket() {
    }

    public Ticket(int sessionId, int rowNumber, int placeNumber, int userId) {
        this.sessionId = sessionId;
        this.rowNumber = rowNumber;
        this.placeNumber = placeNumber;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return id == ticket.id && sessionId == ticket.sessionId
                && rowNumber == ticket.rowNumber
                && placeNumber == ticket.placeNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, rowNumber, placeNumber);
    }

    @Override
    public String toString() {
        return "Ticket{"
                + "id=" + id
                + ", sessionId=" + sessionId
                + ", rowNumber=" + rowNumber
                + ", placeNumber=" + placeNumber
                + ", userId=" + userId
                + '}';
    }
}
