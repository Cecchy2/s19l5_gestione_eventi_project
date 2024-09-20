package dariocecchinato.s19l5_gestione_eventi_project.services;

import dariocecchinato.s19l5_gestione_eventi_project.entities.Utente;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.BadRequestException;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.UnauthorizedException;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.NewUtenteRespDTO;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.SpettatorePayloadDTO;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.UtentiLoginDTO;
import dariocecchinato.s19l5_gestione_eventi_project.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@Service
public class AuthService {
    @Autowired
    private SpettatoriService spettatoriService;
    @Autowired
    private OrganizzatoriService organizzatoriService;
    @Autowired
    private UtentiService utentiService;

    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredenzialiEGeneraToken(UtentiLoginDTO body){
        Utente found = this.utentiService.findUtenteByEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())){
            return this.jwtTools.createToken(found);
        }else{
            throw  new UnauthorizedException("Errore nelle credenziali che hai fornito");
        }
    }




}
