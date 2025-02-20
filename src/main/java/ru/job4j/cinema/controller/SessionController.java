package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.session.FilmSessionService;

@Controller
@RequestMapping("/sessions")
public class SessionController {

    private final FilmSessionService filmSessionService;

    public SessionController(FilmSessionService filmSessionService) {
        this.filmSessionService = filmSessionService;
    }

    @GetMapping
    public String getSessions(Model model) {
        model.addAttribute("filmSessions", filmSessionService.getSessions());
        return "sessions/list";
    }

    @GetMapping("{id}")
    public String get(@PathVariable int id, Model model) {
        var sessionOptional = filmSessionService.getSessionById(id);
        if (sessionOptional.isEmpty()) {
            model.addAttribute("error", "There is no film session, please choose another one.");
            return "errors/404";
        }
        var filmSession = sessionOptional.get();
        model.addAttribute("filmSession", filmSession);
        model.addAttribute("ticket", new Ticket());
        return "sessions/one";
    }
}
