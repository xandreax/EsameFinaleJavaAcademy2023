package it.euris.academy.EsameFinaleJavaAcademy2023.repository;

import it.euris.academy.EsameFinaleJavaAcademy2023.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
