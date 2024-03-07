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
import java.util.Optional;

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
       GiornoSettimana giorniLezione = turnoRequest.getGiorniLezione();
        LocalTime inizioLezione = turnoRequest.getInizioLezione();
        LocalTime fineLezione = turnoRequest.getFineLezione();

        Optional<Turno> existingTurno = turnoRepository.findByTurnoConflict(giorniLezione, inizioLezione, fineLezione);
        if (existingTurno.isPresent()) {
            throw new RuntimeException("Turno con gli stessi valori già presente");
        }

        turno.setGiorniLezione(giorniLezione);
        turno.setInizioLezione(inizioLezione);
        turno.setFineLezione(fineLezione);
        return turnoRepository.save(turno);
    }

    public Turno update(int id, TurnoRequest turnoRequest){
        Turno turno = getTurnoById(id);

        GiornoSettimana giornoLezione = turnoRequest.getGiorniLezione();
        LocalTime inizioLezione = turnoRequest.getInizioLezione();
        LocalTime fineLezione = turnoRequest.getFineLezione();

        Optional<Turno> existingTurno = turnoRepository.findByTurnoConflict(giornoLezione, inizioLezione, fineLezione);
        if (existingTurno.isPresent()) {
            throw new RuntimeException("Turno con gli stessi valori già presente");
        }

            else turno.setGiorniLezione(giornoLezione);
                 turno.setInizioLezione(inizioLezione);
                 turno.setFineLezione(fineLezione);

        return turnoRepository.save(turno);
    }

    public void delete(int id){
        Turno turno = getTurnoById(id);
        turnoRepository.delete(turno);
    }
}
