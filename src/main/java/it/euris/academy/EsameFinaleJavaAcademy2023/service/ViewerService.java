package it.euris.academy.EsameFinaleJavaAcademy2023.service;

import it.euris.academy.EsameFinaleJavaAcademy2023.model.Viewer;
import it.euris.academy.EsameFinaleJavaAcademy2023.model.response.GenericResponse;

import java.util.Date;
import java.util.Optional;

public interface ViewerService {
    Optional<Viewer> getById(int id);
    GenericResponse<Viewer> insert(String name, String surname, Date birthday, int idMovieTheatre, double price, String position);
    GenericResponse<Viewer> update(int id, String name, String surname, Date birthday, int idMovieTheatre, double price, String position);
    Boolean deleteById(int id);
    Boolean deleteAll();
}
