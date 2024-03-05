package it.epicode.pugnatorisClub.request;

import it.epicode.pugnatorisClub.enums.ArtiMarziali;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;


@Data
public class InsegnanteRequest {

    @NotBlank(message = "nome obbligatorio")
    private String nome;

    @NotBlank(message = "cognome obbligatorio")
    private String cognome;

    @NotBlank(message = "discipline obbligatorio")
    private List<ArtiMarziali> discipline;

}
