package ru.job4j.cinema.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.session.FilmSessionService;
import ru.job4j.cinema.service.ticket.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private static final Log LOG = LogFactory.getLog(TicketController.class);

    private final TicketService ticketService;

    private final FilmSessionService filmSessionService;

    public TicketController(TicketService ticketService, FilmSessionService filmSessionService) {
        this.ticketService = ticketService;
        this.filmSessionService = filmSessionService;
    }

    @PostMapping("/buy")
    public String buyTicket(@ModelAttribute Ticket ticket, Model model) {
        var ticketOptional = ticketService.saveTicket(ticket);
        if (ticketOptional.isEmpty()) {
            int row = ticket.getRowNumber();
            int place = ticket.getPlaceNumber();
            var error = String.format(
                    "The ticket with row: %d and place: %d has already been purchased. Please, choose another one.",
                    row, place
            );
            model.addAttribute("error", error);
            LOG.error("Ticket already exists");
            return "errors/404";
        }
        var savedTicket = ticketOptional.get();
        var filmSession = filmSessionService.getSessionById(savedTicket.getSessionId()).get();
        model.addAttribute("filmSession", filmSession);
        model.addAttribute("ticket", savedTicket);
        return "tickets/buy";
    }
}