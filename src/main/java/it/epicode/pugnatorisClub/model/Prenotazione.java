package it.epicode.pugnatorisClub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;


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

    private LocalDate dataPrenotazione;

    private  LocalDate dataScadenza;
}
