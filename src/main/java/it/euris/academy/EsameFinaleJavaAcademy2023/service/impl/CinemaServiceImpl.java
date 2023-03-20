package it.euris.academy.EsameFinaleJavaAcademy2023.service.impl;

import it.euris.academy.EsameFinaleJavaAcademy2023.model.Cinema;
import it.euris.academy.EsameFinaleJavaAcademy2023.model.MovieTheatre;
import it.euris.academy.EsameFinaleJavaAcademy2023.model.response.GenericResponse;
import it.euris.academy.EsameFinaleJavaAcademy2023.repository.CinemaRepository;
import it.euris.academy.EsameFinaleJavaAcademy2023.service.CinemaService;
import it.euris.academy.EsameFinaleJavaAcademy2023.service.MovieTheatreService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;
    private final EntityManager entityManager;

    private final MovieTheatreService movieTheatreService;

    public CinemaServiceImpl(CinemaRepository cinemaRepository, EntityManager entityManager, MovieTheatreService movieTheatreService) {
        this.cinemaRepository = cinemaRepository;
        this.entityManager = entityManager;
        this.movieTheatreService = movieTheatreService;
    }

    @Override
    public Optional<Cinema> getById(int id) {
        return cinemaRepository.findById(id);
    }

    @Override
    public Cinema insert(String name) {
        return cinemaRepository.save(Cinema.builder()
                .name(name)
                .build());
    }

    @Override
    public GenericResponse<Cinema> update(int id, String name) {
        Optional<Cinema> cinema = getById(id);
        GenericResponse<Cinema> response = new GenericResponse<>();
        if (cinema.isPresent()) {
            cinema.get().setName(name);
            cinemaRepository.save(cinema.get());
            response.setBody(cinema.get());
        } else {
            response.setErrorMsg("Cinema con id " + id + " non trovato. Impossibile aggiornare il cinema.");
        }
        return response;
    }

    @Override
    public Boolean deleteById(int id) {
        Boolean res = Boolean.FALSE;
        Optional<Cinema> cinema = getById(id);
        if (cinema.isPresent()) {
            try {
                cinemaRepository.deleteById(id);
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
            cinemaRepository.deleteAll();
            res = Boolean.TRUE;
        } catch (Exception e) {
            res = Boolean.FALSE;
        }
        return res;
    }

    @Override
    public Double getIncasso(int id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MovieTheatre> query = cb.createQuery(MovieTheatre.class);
        Root<MovieTheatre> rootMovieTheatre = query.from(MovieTheatre.class);
        Join<MovieTheatre, Cinema> joinCinema = rootMovieTheatre.join("cinema", JoinType.INNER);
        Path<String> pathIdCinema = joinCinema.get("id");
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(pathIdCinema, id));
        query.select(rootMovieTheatre).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        List<MovieTheatre> movieTheatreList = entityManager.createQuery(query).getResultList();
        return movieTheatreList.stream().mapToDouble(movieTheatre -> movieTheatreService.getIncasso(movieTheatre.getId())).sum();
    }
}
