package it.euris.academy.EsameFinaleJavaAcademy2023.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movietheatres")
public class MovieTheatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int maxSeats;
    private int currentSeats;
    @OneToOne
    @JoinColumn(name = "id")
    private Film currentFilm;

    @ManyToOne
    @JoinColumn(name = "id")
    private Cinema cinema;

}