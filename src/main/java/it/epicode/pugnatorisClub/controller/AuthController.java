package it.epicode.pugnatorisClub.controller;


import it.epicode.pugnatorisClub.exception.BadRequestException;
import it.epicode.pugnatorisClub.exception.LoginFaultException;
import it.epicode.pugnatorisClub.model.Utente;
import it.epicode.pugnatorisClub.request.LoginRequest;
import it.epicode.pugnatorisClub.request.UtenteRequest;
import it.epicode.pugnatorisClub.security.JwtTools;
import it.epicode.pugnatorisClub.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    UtenteService utenteService;

    @Autowired
    JwtTools jwtTools;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/auth/register")
    public Utente register(@RequestBody @Validated UtenteRequest utenteRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }

        return utenteService.save(utenteRequest);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated LoginRequest loginRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }

        Utente user = utenteService.getUtenteByUsername(loginRequest.getUsername());

        if(encoder.matches(loginRequest.getPassword(), user.getPassword())){
            return jwtTools.createToken(user);
        }
        else{
            throw new LoginFaultException("username/password errate");
        }
    }
}
