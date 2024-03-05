package it.epicode.pugnatorisClub.service;

import it.epicode.pugnatorisClub.enums.ArtiMarziali;
import it.epicode.pugnatorisClub.exception.NotFoundException;
import it.epicode.pugnatorisClub.model.Insegnante;
import it.epicode.pugnatorisClub.repository.InsegnanteRepository;
import it.epicode.pugnatorisClub.request.InsegnanteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class InsegnanteService {

    @Autowired
    InsegnanteRepository insegnanteRepository;


    public Page<Insegnante> getAll(Pageable pageable){
        return insegnanteRepository.findAll(pageable);
    }

    public Insegnante getInsegnanteById(int id){
        return insegnanteRepository.findById(id).orElseThrow(()->new NotFoundException("Utente con id = " + id + " non trovato"));
    }

    public Insegnante save(InsegnanteRequest insegnanteRequest){
        Insegnante insegnante = new Insegnante();

        insegnante.setNome(insegnanteRequest.getNome());
        insegnante.setCognome(insegnanteRequest.getCognome());
        insegnante.setDataNascita(insegnanteRequest.getDataNascita());

        return  insegnanteRepository.save(insegnante);
    }

    public Insegnante update(int id, InsegnanteRequest insegnanteRequest){
        Insegnante insegnante = getInsegnanteById(id);
        insegnante.setNome(insegnanteRequest.getNome());
        insegnante.setCognome(insegnanteRequest.getCognome());
        insegnante.setDataNascita(insegnanteRequest.getDataNascita());
        return  insegnanteRepository.save(insegnante);
    }

    public void delete(int id){
        Insegnante insegnante = getInsegnanteById(id);
        insegnanteRepository.delete(insegnante);
    }

    public Insegnante uploadFotoProfilo(int id, String url){
        Insegnante insegnante = getInsegnanteById(id);
        insegnante.setFotoProfilo(url);

        return insegnanteRepository.save(insegnante);

    }

    public Insegnante updateDiscipline(int id, String disciplina) {

        Insegnante insegnante = getInsegnanteById(id);

        if (insegnante.getDiscipline().contains(ArtiMarziali.valueOf(disciplina))) {
            throw new RuntimeException(insegnante.getNome() + " ha già inserito questa disciplina");
        }
        insegnante.addDisciplina(disciplina);
        return insegnanteRepository.save(insegnante);
    }

}

