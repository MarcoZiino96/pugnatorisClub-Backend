package it.epicode.pugnatorisClub.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "corso_id")
    private Corso corso;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;
}
