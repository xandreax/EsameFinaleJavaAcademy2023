package it.euris.academy.EsameFinaleJavaAcademy2023.repository;

import it.euris.academy.EsameFinaleJavaAcademy2023.model.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
}
