package ru.job4j.cinema.repository.ticket;

import ru.job4j.cinema.model.Ticket;

import java.util.Optional;

public interface TicketRepository {

    Optional<Ticket> save(Ticket ticket);

    Optional<Ticket> getTicketBySessionRowNumberAndPlaceNumber(int sessionId, int rowNumber, int placeNumber);
}
