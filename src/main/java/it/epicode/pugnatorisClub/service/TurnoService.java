package it.epicode.pugnatorisClub.service;


import it.epicode.pugnatorisClub.enums.ArtiMarziali;
import it.epicode.pugnatorisClub.enums.GiornoSettimana;
import it.epicode.pugnatorisClub.exception.NotFoundException;
import it.epicode.pugnatorisClub.model.Turno;
import it.epicode.pugnatorisClub.repository.TurnoRepository;
import it.epicode.pugnatorisClub.request.TurnoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TurnoService{

    @Autowired
    TurnoRepository turnoRepository;

    public List<Turno> getAll(){
        return turnoRepository.findAll();
    }

    public Turno getTurnoById(int id){
        return turnoRepository.findById(id).orElseThrow(()-> new NotFoundException("Turno con id =" + id + "non trovato"));
    }

    public Turno save(TurnoRequest turnoRequest){

        Turno turno = new Turno();

       turno.setGiorniLezione(turnoRequest.getGiorniLezione());
       turno.setInizioLezione(turnoRequest.getInizioLezione());
       turno.setFineLezione(turnoRequest.getFineLezione());
       return turnoRepository.save(turno);
    }

    public Turno update(int id, TurnoRequest turnoRequest){
        Turno turno = getTurnoById(id);

        turno.setInizioLezione(turnoRequest.getInizioLezione());
        turno.setFineLezione(turnoRequest.getFineLezione());
        List<GiornoSettimana> giorni = turnoRequest.getGiorniLezione();
        for(GiornoSettimana giorno : giorni){
            if (turno.getGiorniLezione().contains(giorno))
                throw new RuntimeException(" Questo turno ha già inserito questo/i giorno");
            else turno.addGiorno(giorno);
        }
        return turnoRepository.save(turno);
    }

    public void deleteGiornoTurno(int id, GiornoSettimana giorno){
        Turno turno = getTurnoById(id);
        if(turno.getGiorniLezione().isEmpty() || !turno.getGiorniLezione().contains(giorno))
            throw new RuntimeException("La lista è vuota o il giorno non è presente in questo elenco");

        turno.removeGiorno(giorno);
        turnoRepository.save(turno);
    }

    public void delete(int id){
        Turno turno = getTurnoById(id);
        turnoRepository.delete(turno);
    }
}
