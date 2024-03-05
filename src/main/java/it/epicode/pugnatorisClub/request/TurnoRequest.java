package it.epicode.pugnatorisClub.request;

import it.epicode.pugnatorisClub.enums.GiornoSettimana;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.List;

public class TurnoRequest {

    @NotNull(message = "campo obbligatorio")
   private List<GiornoSettimana> giorniLezione;

    @NotNull(message = "campo obbligatorio")
    private LocalTime inizioLezione;

    @NotNull(message = "campo obbligatorio")
    private LocalTime fineLezione;
}
