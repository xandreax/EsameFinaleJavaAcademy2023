package it.euris.academy.EsameFinaleJavaAcademy2023.controller;

import it.euris.academy.EsameFinaleJavaAcademy2023.model.Film;
import it.euris.academy.EsameFinaleJavaAcademy2023.model.response.GenericResponse;
import it.euris.academy.EsameFinaleJavaAcademy2023.service.FilmService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/film")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/{id}")
    public Optional<Film> getById(@PathVariable int id) {
        return filmService.getById(id);
    }

    @PostMapping()
    public Film insert(@RequestBody Film film) {
        return filmService.insert(film.getTitle(), film.getAuthor(), film.getProducer(), film.getGenre(), film.getMinimumAge(), film.getDuration());
    }

    @PutMapping()
    public GenericResponse<Film> update(@RequestBody Film film) {
        return filmService.update(film.getId(), film.getTitle(), film.getAuthor(), film.getProducer(), film.getGenre(), film.getMinimumAge(), film.getDuration());
    }

    @DeleteMapping()
    public Boolean deleteAll() {
        return filmService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable int id) {
        return filmService.deleteById(id);
    }
}
