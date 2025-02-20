package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.service.film.FilmService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FilmControllerTest {

    private FilmService filmService;

    private FilmController filmController;

    @BeforeEach
    public void initService() {
        filmService = mock(FilmService.class);
        filmController = new FilmController(filmService);
    }

    @Test
    public void whenRequestGetFilmListPageThenReturnFilmListPage() {
        var list = List.of(new FilmDto(), new FilmDto(), new FilmDto(), new FilmDto());
        when(filmService.findAllFilms()).thenReturn(list);

        var model = new ConcurrentModel();
        var view = filmController.getFilms(model);
        var expectedList = model.get("films");

        assertThat(view).isEqualTo("films/list");
        assertThat(expectedList).isEqualTo(list);
    }
}