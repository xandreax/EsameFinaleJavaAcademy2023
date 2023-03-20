package it.euris.academy.EsameFinaleJavaAcademy2023.service;

import it.euris.academy.EsameFinaleJavaAcademy2023.model.Film;
import it.euris.academy.EsameFinaleJavaAcademy2023.model.response.GenericResponse;

import java.util.Optional;

public interface FilmService {

    Optional<Film> getById(int id);
    Film insert(String title, String author, String producer, String genre, int minimum_age, int duration);
    GenericResponse<Film> update(int id, String title, String author, String producer, String genre, int minimum_age, int duration);
    Boolean deleteById(int id);
    Boolean deleteAll();
}
