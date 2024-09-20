package dariocecchinato.s19l5_gestione_eventi_project.services;

import dariocecchinato.s19l5_gestione_eventi_project.entities.Spettatore;
import dariocecchinato.s19l5_gestione_eventi_project.entities.Utente;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.UtenteLoginDTO;
import dariocecchinato.s19l5_gestione_eventi_project.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthSercice {
    @Autowired
    private UtentiService utentiService;

    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;

    /*public String checkCredenzialiSpettatoreGeneraToken(UtenteLoginDTO body){
        Utente found =this.utentiService.find
    }*/


}
