package it.euris.academy.EsameFinaleJavaAcademy2023.repository;

import it.euris.academy.EsameFinaleJavaAcademy2023.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
}
