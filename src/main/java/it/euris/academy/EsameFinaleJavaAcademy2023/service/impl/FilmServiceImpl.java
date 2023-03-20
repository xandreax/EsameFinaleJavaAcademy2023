package it.euris.academy.EsameFinaleJavaAcademy2023.service.impl;

import it.euris.academy.EsameFinaleJavaAcademy2023.model.Film;
import it.euris.academy.EsameFinaleJavaAcademy2023.model.response.GenericResponse;
import it.euris.academy.EsameFinaleJavaAcademy2023.repository.FilmRepository;
import it.euris.academy.EsameFinaleJavaAcademy2023.service.FilmService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Optional<Film> getById(int id) {
        return filmRepository.findById(id);
    }

    @Override
    public Film insert(String title, String author, String producer, String genre, int minimum_age, int duration) {
        return filmRepository.save(Film.builder()
                .title(title)
                .author(author)
                .producer(producer)
                .genre(genre)
                .minimumAge(minimum_age)
                .duration(duration)
                .build());
    }

    @Override
    public GenericResponse<Film> update(int id, String title, String author, String producer, String genre, int minimum_age, int duration) {
        Optional<Film> film = getById(id);
        GenericResponse<Film> response = new GenericResponse<>();
        if (film.isPresent()) {
            film.get().setTitle(title);
            film.get().setAuthor(author);
            film.get().setProducer(producer);
            film.get().setGenre(genre);
            film.get().setMinimumAge(minimum_age);
            film.get().setDuration(duration);
            filmRepository.save(film.get());
            response.setBody(film.get());
        } else {
            response.setErrorMsg("Film con id " + id + " non trovato. Impossibile aggiornare il film.");
        }
        return response;
    }

    @Override
    public Boolean deleteById(int id) {
        Boolean res = Boolean.FALSE;
        Optional<Film> film = getById(id);
        if (film.isPresent()) {
            try {
                filmRepository.deleteById(id);
                res = Boolean.TRUE;
            } catch (Exception e) {
                res = Boolean.FALSE;
            }
        }
        return res;
    }

    @Override
    public Boolean deleteAll() {
        Boolean res;
        try {
            filmRepository.deleteAll();
            res = Boolean.TRUE;
        } catch (Exception e) {
            res = Boolean.FALSE;
        }
        return res;
    }
}
