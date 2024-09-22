package dariocecchinato.s19l5_gestione_eventi_project.controllers;

import dariocecchinato.s19l5_gestione_eventi_project.exceptions.BadRequestException;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.NewUtenteRespDTO;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.UtenteLoginDTO;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.UtenteLoginResponseDTO;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.UtentePayloadDTO;
import dariocecchinato.s19l5_gestione_eventi_project.services.AuthorizationService;
import dariocecchinato.s19l5_gestione_eventi_project.services.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/authorizations")
public class AuthorizationController {
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private UtentiService utentiService;

    @PostMapping("/login")
    public UtenteLoginResponseDTO login(@RequestBody UtenteLoginDTO body){
        return new UtenteLoginResponseDTO(this.authorizationService.checkCredenzialiAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteRespDTO save(@RequestBody @Validated UtentePayloadDTO body, BindingResult validationResult){
        if(validationResult.hasErrors()){
            String messages= validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Errori nel Payload. " + messages);
        }else{
            return new NewUtenteRespDTO(this.utentiService.registraUtente(body).getId());
        }
    }
}
