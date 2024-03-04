package it.epicode.pugnatorisClub.model;


import it.epicode.pugnatorisClub.enums.Durata;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Abbonamento {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

   private Durata durata;

   private LocalDate dataAttivazione;

   private LocalDate dataScadenza;

   @ManyToOne
   @JoinColumn(name = "corso_id")
   private Corso corso;

   @ManyToOne
   @JoinColumn(name = "utente_id")
   private Utente utente;
}
