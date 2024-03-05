package it.epicode.pugnatorisClub.repository;

import it.epicode.pugnatorisClub.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long>, PagingAndSortingRepository<Utente,Long> {
    Optional<Utente> findByUsername(String username);
}
