package it.epicode.pugnatorisClub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.pugnatorisClub.enums.Ruolo;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


@Entity
@Data
public class Utente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private  String nome;

    private  String cognonome;

    @Column(unique = true)
    private  String username;

    private String password;

    private LocalDate dataNascita;

    private String email;

    private String fotoProfilo;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    @JsonIgnore
    @OneToMany(mappedBy = "utente")
    private List<Prenotazione> prenotazioni;

    @JsonIgnore
    @OneToMany(mappedBy = "utente")
    private List<Abbonamento> abbonamenti;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(ruolo.name()));
    }
}
