package it.euris.academy.EsameFinaleJavaAcademy2023.service.impl;

import it.euris.academy.EsameFinaleJavaAcademy2023.exceptions.MovieTheatreNotFoundException;
import it.euris.academy.EsameFinaleJavaAcademy2023.exceptions.TheatreIsFullException;
import it.euris.academy.EsameFinaleJavaAcademy2023.exceptions.ViewerIsTooYoungException;
import it.euris.academy.EsameFinaleJavaAcademy2023.model.MovieTheatre;
import it.euris.academy.EsameFinaleJavaAcademy2023.model.Ticket;
import it.euris.academy.EsameFinaleJavaAcademy2023.model.Viewer;
import it.euris.academy.EsameFinaleJavaAcademy2023.model.response.GenericResponse;
import it.euris.academy.EsameFinaleJavaAcademy2023.repository.MovieTheatreRepository;
import it.euris.academy.EsameFinaleJavaAcademy2023.repository.TicketRepository;
import it.euris.academy.EsameFinaleJavaAcademy2023.repository.ViewerRepository;
import it.euris.academy.EsameFinaleJavaAcademy2023.service.ViewerService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ViewerServiceImpl implements ViewerService {

    private final ViewerRepository viewerRepository;
    private final TicketRepository ticketRepository;
    private final MovieTheatreRepository movieTheatreRepository;

    public ViewerServiceImpl(ViewerRepository viewerRepository, TicketRepository ticketRepository, MovieTheatreRepository movieTheatreRepository) {
        this.viewerRepository = viewerRepository;
        this.ticketRepository = ticketRepository;
        this.movieTheatreRepository = movieTheatreRepository;
    }

    @Override
    public Optional<Viewer> getById(int id) {
        return viewerRepository.findById(id);
    }

    @Override
    public GenericResponse<Viewer> insert(String name, String surname, Date birthday, int idMovieTheatre, double price, String position) {
        GenericResponse<Viewer> response = new GenericResponse<>();
        Optional<MovieTheatre> movieTheatre = movieTheatreRepository.findById(idMovieTheatre);
        Viewer viewer = Viewer.builder().name(name).surname(surname).birthday(birthday).build();
        try {
            if (movieTheatre.isPresent()) {
                if (movieTheatre.get().getCurrentSeats() >= movieTheatre.get().getMaxSeats()) {
                    throw new TheatreIsFullException("Sala con id " + idMovieTheatre + " già piena. Impossibile aggiungere uno spettatore a questa sala.");
                }
                if (viewer.getAge() < movieTheatre.get().getCurrentFilm().getMinimumAge()) {
                    throw new ViewerIsTooYoungException("Sala con id " + idMovieTheatre + " ha in proiezione con un film inadatto all'età di questo spettatore. Impossibile aggiungere lo spettatore a questa sala.");
                } else {
                    viewer.setMovieTheatre(movieTheatre.get());
                    Ticket ticket = Ticket.builder().price(price).position(position).build();
                    if (viewer.isOver70()) {
                        ticket.setSeniorsDiscount();
                    }
                    if (viewer.isUnder5()) {
                        ticket.setChildrenDiscount();
                    }
                    Ticket ticketInserted = ticketRepository.save(ticket);
                    viewer.setTicket(ticketInserted);
                    viewerRepository.save(viewer);
                    movieTheatre.get().increaseCurrentSeats();
                    movieTheatreRepository.save(movieTheatre.get());
                }
            } else {
                throw new MovieTheatreNotFoundException("Sala con id " + idMovieTheatre + " non trovata. Impossibile aggiungere uno spettatore a questa sala.");
            }
        } catch (TheatreIsFullException | ViewerIsTooYoungException | MovieTheatreNotFoundException e) {
            response.setErrorMsg(e.getMessage());
        }
        return response;
    }

    @Override
    public GenericResponse<Viewer> update(int id, String name, String surname, Date birthday, int idMovieTheatre, double price, String position) {
        return null;
    }

    @Override
    public Boolean deleteById(int id) {
        Boolean res = Boolean.FALSE;
        Optional<Viewer> viewer = getById(id);
        if (viewer.isPresent()) {
            try {
                int idTicket = viewer.get().getTicket().getId();
                viewerRepository.deleteById(id);
                ticketRepository.deleteById(idTicket);
                res = Boolean.TRUE;
            } catch (Exception e) {
                res = Boolean.FALSE;
            }
        }
        return res;
    }

    @Override
    public Boolean deleteAll() {
        Boolean res;
        try {
            viewerRepository.deleteAll();
            ticketRepository.deleteAll();
            res = Boolean.TRUE;
        } catch (Exception e) {
            res = Boolean.FALSE;
        }
        return res;
    }
}
