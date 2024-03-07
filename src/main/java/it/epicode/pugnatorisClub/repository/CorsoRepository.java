package it.epicode.pugnatorisClub.repository;

import it.epicode.pugnatorisClub.enums.ArtiMarziali;
import it.epicode.pugnatorisClub.model.Corso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CorsoRepository extends JpaRepository<Corso, Long> {

    @Query("SELECT c FROM Corso c WHERE c.categoria = :categoria")
    Optional<Corso> findByCategoria(ArtiMarziali categoria);
}
