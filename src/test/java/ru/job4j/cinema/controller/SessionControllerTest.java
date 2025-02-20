package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.session.FilmSessionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SessionControllerTest {

    private FilmSessionService filmSessionService;

    private SessionController sessionController;

    @BeforeEach
    public void initService() {
        filmSessionService = mock(FilmSessionService.class);
        sessionController = new SessionController(filmSessionService);
    }

    @Test
    public void whenRequestGetSessionFilmListPageThenReturnFilmSessionListPage() {
        var oneFilmSessionDto = new FilmSessionDto(1, LocalDateTime.MIN, LocalDateTime.MAX, 0);
        var twoFilmSessionDto = new FilmSessionDto(2, LocalDateTime.MIN, LocalDateTime.MAX, 1);
        var sessionList = List.of(oneFilmSessionDto, twoFilmSessionDto);
        when(filmSessionService.getSessions()).thenReturn(sessionList);

        var model = new ConcurrentModel();
        var view = sessionController.getSessions(model);
        var expectedList = model.getAttribute("filmSessions");

        assertThat(view).isEqualTo("sessions/list");
        assertThat(expectedList).isEqualTo(sessionList);
    }

    @Test
    public void whenRequestGetSessionFilmSessionDtoPageThenReturnFilmSessionDtoPage() {
        int id = 1;
        var sessionDto = new FilmSessionDto(id, LocalDateTime.MIN, LocalDateTime.MAX, 1000);
        var idArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        when(filmSessionService.getSessionById(idArgumentCaptor.capture())).thenReturn(Optional.of(sessionDto));

        var model = new ConcurrentModel();
        var view = sessionController.get(id, model);
        var actualSessionDto = model.getAttribute("filmSession");
        var ticket = model.getAttribute("ticket");
        var actualId = idArgumentCaptor.getValue();

        assertThat(actualSessionDto).isEqualTo(sessionDto);
        assertThat(actualId).isEqualTo(id);
        assertThat(ticket).isEqualTo(new Ticket());
        assertThat(view).isEqualTo("sessions/one");
    }

    @Test
    public void whenRequestGetFilmSessionDtoPageByNotExistedIdThenReturnErrorPage() {
        when(filmSessionService.getSessionById(any(Integer.class))).thenReturn(Optional.empty());

        var model = new ConcurrentModel();
        var view = sessionController.get(-1, model);
        var errorMessage = model.getAttribute("error");

        assertThat(errorMessage).isEqualTo("There is no film session, please choose another one.");
        assertThat(view).isEqualTo("errors/404");
    }
}