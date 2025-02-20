package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.session.FilmSessionService;
import ru.job4j.cinema.service.ticket.TicketService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TicketControllerTest {

    private TicketService ticketService;

    private TicketController ticketController;

    private FilmSessionService filmSessionService;

    @BeforeEach
    public void initService() {
        ticketService = mock(TicketService.class);
        filmSessionService = mock(FilmSessionService.class);
        ticketController = new TicketController(ticketService, filmSessionService);
    }

    @Test
    public void whenRequestBuyTicketThenReturnTicketBuyPage() {
        int id = 1;
        var ticket = new Ticket(id, 2, 3, 4);
        var filmSession = new FilmSessionDto(id, LocalDateTime.now(), LocalDateTime.now().plusHours(3), 1000);
        when(ticketService.saveTicket(any(Ticket.class))).thenReturn(Optional.of(ticket));
        when(filmSessionService.getSessionById(any(Integer.class))).thenReturn(Optional.of(filmSession));

        var model = new ConcurrentModel();
        var view = ticketController.buyTicket(ticket, model);
        var actualFilmSession = model.getAttribute("filmSession");
        var actualTicket = model.getAttribute("ticket");

        assertThat(actualFilmSession).isEqualTo(filmSessionService.getSessionById(id).get());
        assertThat(actualTicket).isEqualTo(ticket);
        assertThat(view).isEqualTo("tickets/buy");
    }

    @Test
    public void whenRequestAttemptExitTicketThenReturnErrorPage() {
        var ticket = new Ticket(1, 2, 3, 4);
        int row = ticket.getRowNumber();
        int place = ticket.getPlaceNumber();
        when(ticketService.saveTicket(any(Ticket.class))).thenReturn(Optional.empty());

        var model = new ConcurrentModel();
        var view = ticketController.buyTicket(ticket, model);
        var error = model.getAttribute("error");
        var errorMessage = String.format(
                "The ticket with row: %d and place: %d has already been purchased. Please, choose another one.",
                row, place
        );

        assertThat(error).isEqualTo(errorMessage);
        assertThat(view).isEqualTo("errors/404");
    }

}