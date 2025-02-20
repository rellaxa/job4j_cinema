package ru.job4j.cinema.service.session;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.repository.session.FilmSessionRepository;
import ru.job4j.cinema.repository.hall.HallRepository;
import ru.job4j.cinema.service.film.FilmService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleFilmSessionService implements FilmSessionService {

    private final FilmSessionRepository filmSessionRepository;

    private final FilmService filmService;

    private final HallRepository hallRepository;

    public SimpleFilmSessionService(FilmSessionRepository filmSessionRepository, FilmService filmService, HallRepository hallRepository) {
        this.filmSessionRepository = filmSessionRepository;
        this.filmService = filmService;
        this.hallRepository = hallRepository;
    }

    @Override
    public Collection<FilmSessionDto> getSessions() {
        var sessions = filmSessionRepository.getFilmSessions();
        return getFilmSessionsDto(sessions);
    }

    @Override
    public Optional<FilmSessionDto> getSessionById(int sessionId) {
        var session = filmSessionRepository.getById(sessionId);
        if (session.isEmpty()) {
            return Optional.empty();
        }
        var sessionDto = getFilmSessionDto(session.get());
        return Optional.of(sessionDto);
    }

    private Collection<FilmSessionDto> getFilmSessionsDto(Collection<FilmSession> sessions) {
        var sessionListDto = new ArrayList<FilmSessionDto>();
        for (var session : sessions) {
            sessionListDto.add(getFilmSessionDto(session));
        }
        return sessionListDto;
    }

    private FilmSessionDto getFilmSessionDto(FilmSession session) {
        var filmSessionDto = new FilmSessionDto(
                session.getId(),
                session.getStartTime(),
                session.getEndTime(),
                session.getPrice()
        );
        var film = filmService.getFilmById(session.getId()).get();
        var hall = hallRepository.findById(session.getHallId()).get();
        var fileId = film.getFileId();

        filmSessionDto.setFilmName(film.getName());
        filmSessionDto.setHall(hall);
        filmSessionDto.setFileId(fileId);
        return filmSessionDto;
    }

}
