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

    private String avatar;

    private String fotoProfilo;

    @Enumerated(EnumType.STRING)
    private List<Ruolo> ruoli= List.of(Ruolo.USER);

//    @JsonIgnore
//    @ManyToMany(mappedBy = "utentiIscritti", cascade = CascadeType.REMOVE)
//    private List<Corso> corsiAttivi;

    @JsonIgnore
    @OneToMany(mappedBy = "utente")
    private List<Prenotazione> prenotazioni;

    @JsonIgnore
    @OneToMany(mappedBy = "utente")
    private List<Abbonamento> abbonamenti;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(ruoli.size());

        for (Ruolo r : ruoli)
            authorities.add(new SimpleGrantedAuthority(r.name()));

        return authorities;
    }
}
