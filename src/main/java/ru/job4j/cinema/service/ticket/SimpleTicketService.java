package ru.job4j.cinema.service.ticket;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.repository.ticket.TicketRepository;

import java.util.Optional;

@Service
public class SimpleTicketService implements TicketService {

    private final TicketRepository ticketRepository;

    public SimpleTicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Optional<Ticket> saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
}