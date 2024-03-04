package it.epicode.pugnatorisClub.model;




import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.pugnatorisClub.enums.Durata;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String categoria;

    private String descrizione;

    private int numeroMassimoPartecipanti;

    private double costoMensile;

    private Durata durata;

    @JsonIgnore
    @OneToMany(mappedBy = "corso", cascade = CascadeType.REMOVE)
    private List<Turno> turni;

    @JsonIgnore
    @OneToMany(mappedBy = "corso", cascade = CascadeType.REMOVE)
    private List<Prenotazione> prenotazioni;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "insegnante_id")
    private Insegnante maestro;

    @JsonIgnore
    @OneToMany(mappedBy = "corso",  cascade = CascadeType.REMOVE)
    private List<Abbonamento> abbonati;
}
