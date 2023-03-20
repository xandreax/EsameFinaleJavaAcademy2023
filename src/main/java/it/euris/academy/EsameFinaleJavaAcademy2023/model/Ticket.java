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
@Table(name = "tickets")
public class Ticket {

    private static double SENIORS_OVER70_DISCOUNT = 0.1;
    private static double CHILDREN_UNDER5_DISCOUNT = 0.5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String position;
    private double priceTicket;


    public void getSeniorsDiscount(){

    }

    public void getChildrenDiscount(){

    }

}
