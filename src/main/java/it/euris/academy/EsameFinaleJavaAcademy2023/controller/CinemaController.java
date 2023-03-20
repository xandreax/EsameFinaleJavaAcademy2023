package it.euris.academy.EsameFinaleJavaAcademy2023.controller;

import it.euris.academy.EsameFinaleJavaAcademy2023.model.Cinema;
import it.euris.academy.EsameFinaleJavaAcademy2023.model.response.GenericResponse;
import it.euris.academy.EsameFinaleJavaAcademy2023.service.CinemaService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cinema")
public class CinemaController {
    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/{id}")
    public Optional<Cinema> getById(@PathVariable int id) {
        return cinemaService.getById(id);
    }

    @GetMapping("/incasso/{id}")
    public Double getIncassoSaleByIdCinema(@PathVariable int id) {
        return cinemaService.getIncasso(id);
    }

    @PostMapping()
    public Cinema insert(@RequestBody Cinema cinema) {
        return cinemaService.insert(cinema.getName());
    }

    @PutMapping()
    public GenericResponse<Cinema> update(@RequestBody Cinema cinema) {
        return cinemaService.update(cinema.getId(), cinema.getName());
    }

    @DeleteMapping()
    public Boolean deleteAll() {
        return cinemaService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable int id) {
        return cinemaService.deleteById(id);
    }
}
