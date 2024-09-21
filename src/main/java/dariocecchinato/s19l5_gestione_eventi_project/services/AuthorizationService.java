package dariocecchinato.s19l5_gestione_eventi_project.services;

import dariocecchinato.s19l5_gestione_eventi_project.entities.Utente;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.UnauthorizedException;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.UtenteLoginDTO;
import dariocecchinato.s19l5_gestione_eventi_project.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    @Autowired
    private UtentiService utentiService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredenzialiAndGenerateToken(UtenteLoginDTO body){
        Utente found= this.utentiService.findUtenteByEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())){
            return this.jwtTools.createToken(found);
        }else{
            throw new UnauthorizedException("Errore nelle credenziali che hai fornito");
        }
    }
   /* public String checkCredenzialiAndGenerateToken(UtenteLoginDTO body) {
        Utente found = this.utentiService.findUtenteByEmail(body.email());
        if (found == null) {
            throw new UnauthorizedException("Email non trovata");
        }

        System.out.println("Password fornita: " + body.password());
        System.out.println("Password salvata: " + found.getPassword());

        if (!body.password().equals(found.getPassword())) {
            throw new UnauthorizedException("Errore nelle credenziali che hai fornito");
        }

        return this.jwtTools.createToken(found);
    }*/
}
