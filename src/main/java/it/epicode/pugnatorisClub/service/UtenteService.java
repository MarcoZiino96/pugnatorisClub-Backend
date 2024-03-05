package it.epicode.pugnatorisClub.service;


import it.epicode.pugnatorisClub.enums.Ruolo;
import it.epicode.pugnatorisClub.exception.NotFoundException;
import it.epicode.pugnatorisClub.model.Utente;
import it.epicode.pugnatorisClub.repository.UtenteRepository;
import it.epicode.pugnatorisClub.request.UtenteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    PasswordEncoder encoder;

//    @Autowired
//    private JavaMailSenderImpl javaMailSender;

    public Page<Utente> getAll(Pageable pageable){
        return utenteRepository.findAll(pageable);
    }

    public Utente getUtenteById(long id){
        return utenteRepository.findById(id).orElseThrow(()->new NotFoundException("Utente con id = " + id + " non trovato"));
    }

    public Utente getUtenteByUsername(String user){
         return utenteRepository.findByUsername(user).orElseThrow(()->new NotFoundException("Utente con username = " + user + " non trovato"));
    }

    public Utente save(UtenteRequest utenteRequest){
        Utente utente = new Utente();

        utente.setNome(utenteRequest.getNome());
        utente.setCognonome(utenteRequest.getCognome());
        utente.setUsername(utenteRequest.getUsername());
        utente.setDataNascita(utenteRequest.getDataNascita());
        utente.setPassword(encoder.encode(utenteRequest.getPassword()));
        utente.setRuolo(Ruolo.USER);
        utente.setEmail(utenteRequest.getEmail());
//        sendEmail(utenteRequest.getEmail());
        return  utenteRepository.save(utente);
    }

    public Utente update(long id, UtenteRequest utenteRequest){
        Utente utente = getUtenteById(id);
        utente.setNome(utenteRequest.getNome());
        utente.setCognonome(utenteRequest.getCognome());
        utente.setUsername(utenteRequest.getUsername());
        utente.setDataNascita(utenteRequest.getDataNascita());
        utente.setEmail(utenteRequest.getEmail());
        utente.setPassword(encoder.encode(utenteRequest.getPassword()));
        return utenteRepository.save(utente);
    }

    public void delete(long id){
        Utente utente = getUtenteById(id);
        utenteRepository.delete(utente);
    }

    public Utente updateRole(String username, String ruolo) throws NotFoundException {
        Utente u = getUtenteByUsername(username);
        u.setRuolo(Ruolo.valueOf(ruolo));
        return utenteRepository.save(u);

    }

    public Utente uploadFotoProfilo(long id, String url){
        Utente u = getUtenteById(id);
        u.setFotoProfilo(url);

        return utenteRepository.save(u);

    }

//    public void sendEmail(String email){
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject("Registrazione alla palestra PugnatorisClub");
//        message.setText("Benvenuto sulla nostra applicazione, registrazione avvenuta con successo.Dai un occhiata ai nostri corsi e prenota una lezione di prova gratuita");
//        javaMailSender.send(message);
//    }
}
