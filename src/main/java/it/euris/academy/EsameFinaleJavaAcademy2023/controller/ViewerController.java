package it.euris.academy.EsameFinaleJavaAcademy2023.controller;

import it.euris.academy.EsameFinaleJavaAcademy2023.model.Viewer;
import it.euris.academy.EsameFinaleJavaAcademy2023.model.request.ViewerTicketDTO;
import it.euris.academy.EsameFinaleJavaAcademy2023.model.response.GenericResponse;
import it.euris.academy.EsameFinaleJavaAcademy2023.service.ViewerService;
import it.euris.academy.EsameFinaleJavaAcademy2023.util.ConverterStringToDate;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/viewer")
public class ViewerController {
    private final ViewerService viewerService;

    public ViewerController(ViewerService viewerService) {
        this.viewerService = viewerService;
    }

    @GetMapping("/{id}")
    public Optional<Viewer> getById(@PathVariable int id) {
        return viewerService.getById(id);
    }

    @PostMapping()
    public GenericResponse<Viewer> insert(@RequestBody ViewerTicketDTO viewer) {
        try {
            Date birthday = ConverterStringToDate.convertToDate(viewer.getBirthday());
            return viewerService.insert(viewer.getName(), viewer.getSurname(), birthday, viewer.getMovieTheatreId(), viewer.getPrice(), viewer.getPosition());
        } catch (ParseException e) {
            GenericResponse<Viewer> response = new GenericResponse<>();
            response.setErrorMsg("Errore nel leggere la data dello spettatore, per favore inserisci la data nel formato dd/MM/yyyy");
            return response;
        }
    }

    @PutMapping()
    public GenericResponse<Viewer> update(@RequestBody ViewerTicketDTO viewer) {
        try {
            Date birthday = ConverterStringToDate.convertToDate(viewer.getBirthday());
            return viewerService.update(viewer.getId(), viewer.getName(), viewer.getSurname(), birthday, viewer.getMovieTheatreId(), viewer.getPrice(), viewer.getPosition());
        } catch (ParseException e) {
            GenericResponse<Viewer> response = new GenericResponse<>();
            response.setErrorMsg("Errore nel leggere la data dello spettatore, per favore inserisci la data nel formato dd/MM/yyyy");
            return response;
        }
    }

    @DeleteMapping()
    public Boolean deleteAll() {
        return viewerService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable int id) {
        return viewerService.deleteById(id);
    }
}
