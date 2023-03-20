package it.euris.academy.EsameFinaleJavaAcademy2023.controller;

import it.euris.academy.EsameFinaleJavaAcademy2023.model.MovieTheatre;
import it.euris.academy.EsameFinaleJavaAcademy2023.model.response.GenericResponse;
import it.euris.academy.EsameFinaleJavaAcademy2023.service.MovieTheatreService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/movie_theatre")
public class MovieTheatreController {

    private final MovieTheatreService movieTheatreService;

    public MovieTheatreController(MovieTheatreService movieTheatreService) {
        this.movieTheatreService = movieTheatreService;
    }

    @GetMapping("/{id}")
    public Optional<MovieTheatre> getById(@PathVariable int id){
        return movieTheatreService.getById(id);
    }

    @GetMapping("/empty/{id}")
    public Boolean emptyMovieTheatreById(@PathVariable int id){
        return movieTheatreService.emptyTheatre(id);
    }

    @PostMapping()
    public GenericResponse<MovieTheatre> insert(@RequestBody MovieTheatre movieTheatre){
        return movieTheatreService.insert(movieTheatre.getMaxSeats(), movieTheatre.getFilmId(), movieTheatre.getCinemaId());
    }

    @PutMapping()
    public GenericResponse<MovieTheatre> update(@RequestBody MovieTheatre movieTheatre){
        return movieTheatreService.update(movieTheatre.getId(), movieTheatre.getMaxSeats(), movieTheatre.getCurrentSeats(), movieTheatre.getFilmId(), movieTheatre.getCinemaId());
    }

    @DeleteMapping()
    public Boolean deleteAll() {
        return movieTheatreService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable int id) {
        return movieTheatreService.deleteById(id);
    }
}
