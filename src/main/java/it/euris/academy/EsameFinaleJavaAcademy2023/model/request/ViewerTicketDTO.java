package it.euris.academy.EsameFinaleJavaAcademy2023.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewerTicketDTO {
    private int id;
    private String name;
    private String surname;
    private String birthday;
    private int MovieTheatreId;
    private String position;
    private double price;
}
