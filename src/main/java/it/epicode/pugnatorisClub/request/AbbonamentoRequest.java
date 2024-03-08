package it.epicode.pugnatorisClub.request;

import it.epicode.pugnatorisClub.enums.Durata;
import it.epicode.pugnatorisClub.model.Corso;
import it.epicode.pugnatorisClub.model.Utente;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class AbbonamentoRequest {

    @NotNull(message = "categoria obbligatoria")
    private Durata durata;

    @NotNull(message = "categoria obbligatoria")
    private LocalDate dataAttivazione;

    @NotNull(message = "categoria obbligatoria")
    private Corso corso;

    @NotNull(message = "categoria obbligatoria")
    private Utente utente;
}
