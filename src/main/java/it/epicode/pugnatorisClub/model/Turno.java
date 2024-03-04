package it.epicode.pugnatorisClub.model;

import it.epicode.pugnatorisClub.enums.GiornoSettimana;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private List<GiornoSettimana> giorniLezione;

    private LocalDateTime inizioLezione;

    private LocalDateTime fineLezione;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Corso corso;

}
