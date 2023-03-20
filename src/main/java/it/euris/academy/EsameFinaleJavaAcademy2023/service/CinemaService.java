package it.euris.academy.EsameFinaleJavaAcademy2023.service;

import it.euris.academy.EsameFinaleJavaAcademy2023.model.Cinema;
import it.euris.academy.EsameFinaleJavaAcademy2023.model.response.GenericResponse;

import java.util.Optional;

public interface CinemaService {

    Optional<Cinema> getById(int id);

    Cinema insert(String name);

    GenericResponse<Cinema> update(int id, String name);

    Boolean deleteById(int id);

    Boolean deleteAll();

    Double getIncasso(int idCinema);
}
