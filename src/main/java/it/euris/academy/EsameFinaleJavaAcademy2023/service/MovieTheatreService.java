package it.euris.academy.EsameFinaleJavaAcademy2023.service;

import it.euris.academy.EsameFinaleJavaAcademy2023.model.MovieTheatre;
import it.euris.academy.EsameFinaleJavaAcademy2023.model.response.GenericResponse;

import java.util.Optional;

public interface MovieTheatreService {
    Optional<MovieTheatre> getById(int id);
    GenericResponse<MovieTheatre> insert (int maxSeats, int filmId, int cinemaId);
    GenericResponse<MovieTheatre> update (int id, int maxSeats, int currentSeats, int filmId, int cinemaId);
    Boolean emptyTheatre(int id);
    Double getIncasso(int idMovieTheatre);
    Boolean deleteById(int id);
    Boolean deleteAll();
}
