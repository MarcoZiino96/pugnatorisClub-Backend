package it.epicode.pugnatorisClub.model;


import it.epicode.pugnatorisClub.enums.Durata;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Abbonamento {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

   private Durata durata;

   @ManyToOne
   @JoinColumn(name = "corso_id")
   private Corso corso;


   @ManyToOne
   @JoinColumn(name = "utente_id")
   private Utente utente;
}
