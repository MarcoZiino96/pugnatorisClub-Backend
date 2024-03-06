package it.epicode.pugnatorisClub.model;

import it.epicode.pugnatorisClub.enums.GiornoSettimana;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    private List<GiornoSettimana> giorniLezione = new ArrayList<>();

    private LocalTime inizioLezione;

    private LocalTime fineLezione;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Corso corso;


        public void addGiorno(GiornoSettimana giorno){
            giorniLezione.add(giorno);
        }


    public void removeGiorno(GiornoSettimana giorno){
            giorniLezione.remove(giorno);
    }
}
