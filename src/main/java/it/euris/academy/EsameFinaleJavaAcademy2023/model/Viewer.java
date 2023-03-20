package it.euris.academy.EsameFinaleJavaAcademy2023.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "viewers")
public class Viewer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;
    private Date birthday;
    @OneToOne
    @JoinColumn
    private Ticket ticket;

    @ManyToOne
    @JoinColumn
    private MovieTheatre movieTheatre;

    public int getAge() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(new java.sql.Date(birthday.getTime()).toLocalDate(), currentDate).getYears();
    }

    public boolean isAdult() {
        return getAge() > 18;
    }

    public boolean isUnder5() {
        return getAge() < 5;
    }

    public boolean isOver70() {
        return getAge() > 70;
    }
}
