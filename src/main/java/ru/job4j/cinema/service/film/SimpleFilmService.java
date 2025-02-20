package ru.job4j.cinema.service.film;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.repository.film.FilmRepository;
import ru.job4j.cinema.repository.genre.GenreRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleFilmService implements FilmService {

    private final FilmRepository filmRepository;

    private final GenreRepository genreRepository;

    public SimpleFilmService(FilmRepository filmRepository, GenreRepository genreRepository) {
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public Optional<FilmDto> getFilmById(int id) {
        var film = filmRepository.findById(id);
        if (film.isEmpty()) {
            return Optional.empty();
        }
        var filmDto = getFilmWithGenre(film.get());
        return Optional.of(filmDto);
    }

    @Override
    public Collection<FilmDto> findAllFilms() {
        var films = filmRepository.getFilms();
        return getFilmsWithGenre(films);
    }

    private Collection<FilmDto> getFilmsWithGenre(Collection<Film> films) {
        var filmDtoList = new ArrayList<FilmDto>();
        for (var film : films) {
            var filmDto = getFilmWithGenre(film);
            filmDtoList.add(filmDto);
        }
        return filmDtoList;
    }

    private FilmDto getFilmWithGenre(Film film) {
        var filmDto = new FilmDto(
                film.getId(),
                film.getName(),
                film.getDescription(),
                film.getYear(),
                film.getMinimalAge(),
                film.getDurationInMinutes(),
                film.getFileId()
        );
        var genre = genreRepository.findById(film.getGenreId()).getName();
        filmDto.setGenre(genre);
        return filmDto;
    }
}