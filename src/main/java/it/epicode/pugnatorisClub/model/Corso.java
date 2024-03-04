package it.epicode.pugnatorisClub.model;




import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.pugnatorisClub.enums.Durata;
import jakarta.persistence.*;
import lombok.Data;

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

    private Durata durata;

//    @ManyToMany(cascade = CascadeType.REMOVE)
//    @JoinTable(name = "corso_utente",
//    joinColumns = @JoinColumn(name = "corso_id"),
//    inverseJoinColumns = @JoinColumn(name = "utente_id"))
//    private List<Utente> utentiIscritti;

    @JsonIgnore
    @OneToMany(mappedBy = "corso")
    private List<Turno> turni;

    @JsonIgnore
    @OneToMany(mappedBy = "corso")
    private List<Prenotazione> prenotazioni;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "insegnante_id")
    private Insegnante maestro;

    @JsonIgnore
    @OneToMany(mappedBy = "corso")
    private List<Abbonamento> abbonati;
}
