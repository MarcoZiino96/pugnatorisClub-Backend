package it.epicode.pugnatorisClub.model;

import it.epicode.pugnatorisClub.enums.ArtiMarziali;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Insegnante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    private String cognome;

    private LocalDate dataNascita;

    private List<ArtiMarziali> discipline;

    public Insegnante() {
        this.discipline = new ArrayList<>();
    }

    private String fotoProfilo;

    @OneToMany(mappedBy = "maestro")
    private List<Corso> corsi;


    public void addDisciplina(String artiMarziali){
        discipline.add(ArtiMarziali.valueOf(artiMarziali));
    }

    public  void removeDisciplina(ArtiMarziali artiMarziali){
        discipline.remove(artiMarziali);
    }
}
