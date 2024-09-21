package dariocecchinato.s19l5_gestione_eventi_project.controllers;

import dariocecchinato.s19l5_gestione_eventi_project.payloads.UtenteLoginDTO;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.UtenteLoginResponseDTO;
import dariocecchinato.s19l5_gestione_eventi_project.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorizations")
public class AuthorizationController {
    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/login")
    public UtenteLoginResponseDTO login(@RequestBody UtenteLoginDTO body){
        return new UtenteLoginResponseDTO(this.authorizationService.checkCredenzialiAndGenerateToken(body));
    }
}
