package dariocecchinato.s19l5_gestione_eventi_project.controllers;

import dariocecchinato.s19l5_gestione_eventi_project.exceptions.BadRequestException;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.NewUtenteRespDTO;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.OrganizzatorePayloadDTO;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.UtentiLoginDTO;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.UtenteLoginResponseDTO;
import dariocecchinato.s19l5_gestione_eventi_project.services.AuthService;
import dariocecchinato.s19l5_gestione_eventi_project.services.OrganizzatoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private OrganizzatoriService organizzatoriService;

@PostMapping("/login")
    public UtenteLoginResponseDTO login(@RequestBody UtentiLoginDTO body){
    return new UtenteLoginResponseDTO(this.authService.checkCredenzialiEGeneraToken(body));
    }
    /*@PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteRespDTO save(@RequestBody @Validated OrganizzatorePayloadDTO body, BindingResult validationResult){
        if(validationResult.hasErrors()){
            String messages= validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Errori nel Payload. " + messages);
        }else{
            return new NewUtenteRespDTO(this.organizzatoriService.save(body).getId());
        }
    }*/
}



