package it.euris.academy.EsameFinaleJavaAcademy2023.service.impl;

import it.euris.academy.EsameFinaleJavaAcademy2023.model.Cinema;
import it.euris.academy.EsameFinaleJavaAcademy2023.model.Film;
import it.euris.academy.EsameFinaleJavaAcademy2023.model.MovieTheatre;
import it.euris.academy.EsameFinaleJavaAcademy2023.model.Viewer;
import it.euris.academy.EsameFinaleJavaAcademy2023.model.response.GenericResponse;
import it.euris.academy.EsameFinaleJavaAcademy2023.repository.CinemaRepository;
import it.euris.academy.EsameFinaleJavaAcademy2023.repository.FilmRepository;
import it.euris.academy.EsameFinaleJavaAcademy2023.repository.MovieTheatreRepository;
import it.euris.academy.EsameFinaleJavaAcademy2023.service.MovieTheatreService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieTheatreServiceImpl implements MovieTheatreService {
    private final EntityManager entityManager;
    private final MovieTheatreRepository movieTheatreRepository;
    private final CinemaRepository cinemaRepository;
    private final FilmRepository filmRepository;

    public MovieTheatreServiceImpl(EntityManager entityManager, MovieTheatreRepository movieTheatreRepository, CinemaRepository cinemaRepository, FilmRepository filmRepository) {
        this.entityManager = entityManager;
        this.movieTheatreRepository = movieTheatreRepository;
        this.cinemaRepository = cinemaRepository;
        this.filmRepository = filmRepository;
    }

    @Override
    public Optional<MovieTheatre> getById(int id) {
        return movieTheatreRepository.findById(id);
    }

    @Override
    public GenericResponse<MovieTheatre> insert(int maxSeats, int filmId, int cinemaId) {
        GenericResponse<MovieTheatre> response = new GenericResponse<>();
        Optional<Cinema> cinema = cinemaRepository.findById(cinemaId);
        if(cinema.isPresent()){
            Optional<Film> film = filmRepository.findById(filmId);
            if(film.isPresent()){
                MovieTheatre movieTheatre = movieTheatreRepository.save(MovieTheatre.builder()
                        .cinema(cinema.get())
                        .currentFilm(film.get())
                        .currentSeats(0)
                        .maxSeats(maxSeats)
                        .build());
                response.setBody(movieTheatre);
            } else {
                response.setErrorMsg("Film con id " + filmId + " non trovato. Impossibile aggiungere la sala.");
            }
        } else {
            response.setErrorMsg("Cinema con id " + cinemaId + " non trovato. Impossibile aggiungere la sala.");
        }
        return response;
    }

    @Override
    public GenericResponse<MovieTheatre> update(int id, int maxSeats, int currentSeats, int filmId, int cinemaId) {
        return null;
    }

    @Override
    public Boolean emptyTheatre(int id) {
        Boolean res = Boolean.FALSE;
        Optional<MovieTheatre> movieTheatre = getById(id);
        if (movieTheatre.isPresent()) {
            try {
                movieTheatre.get().setCurrentSeats(0);
                movieTheatreRepository.save(movieTheatre.get());
                res = Boolean.TRUE;
            } catch (Exception e) {
                res = Boolean.FALSE;
            }
        }
        return res;
    }

    @Override
    public Double getIncasso(int idMovieTheatre) {
        List<Viewer> viewers = getViewerOfMovieTheatre(idMovieTheatre);
        return viewers.stream().mapToDouble(viewer -> viewer.getTicket().getPrice()).sum();
    }

    @Override
    public Boolean deleteById(int id) {
        Boolean res = Boolean.FALSE;
        Optional<MovieTheatre> movieTheatre = getById(id);
        if (movieTheatre.isPresent()) {
            try {
                movieTheatreRepository.deleteById(id);
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
            movieTheatreRepository.deleteAll();
            res = Boolean.TRUE;
        } catch (Exception e) {
            res = Boolean.FALSE;
        }
        return res;
    }

    private List<Viewer> getViewerOfMovieTheatre(int idMovieTheatre){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Viewer> query = cb.createQuery(Viewer.class);
        Root<Viewer> rootViewer = query.from(Viewer.class);
        Join<Viewer, MovieTheatre> joinTheatre = rootViewer.join("movietheatres", JoinType.INNER);
        Path<String> pathIdMovieTheatre = joinTheatre.get("id");
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(pathIdMovieTheatre, idMovieTheatre));
        query.select(rootViewer).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(query).getResultList();
    }
}
