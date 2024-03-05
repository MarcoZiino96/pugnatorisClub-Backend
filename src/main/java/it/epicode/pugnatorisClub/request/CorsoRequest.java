package it.epicode.pugnatorisClub.request;

import it.epicode.pugnatorisClub.enums.Durata;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CorsoRequest {

    @NotNull(message = "categoria obbligatoria")
    private String categoria;

    @NotNull(message = "descrizione obbligatoria")
    private String descrizione;

    @NotNull(message = "numero massimo partecipanti obbligatorio")
    private int numeroMassimoPartecipanti;

    @NotBlank(message = "durata del corso obbligatoria")
    private Durata durata;
}
