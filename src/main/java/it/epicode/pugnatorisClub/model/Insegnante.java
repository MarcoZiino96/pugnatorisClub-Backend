package it.epicode.pugnatorisClub.model;

import it.epicode.pugnatorisClub.enums.ArtiMarziali;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Insegnante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    private String cognome;

    private List<ArtiMarziali> discipline;

    private String fotoProfilo;

    @OneToMany(mappedBy = "maestro")
    private List<Corso> corsi;
}
