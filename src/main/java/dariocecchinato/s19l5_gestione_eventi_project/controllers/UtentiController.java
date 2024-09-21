package dariocecchinato.s19l5_gestione_eventi_project.controllers;


import dariocecchinato.s19l5_gestione_eventi_project.entities.Utente;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.BadRequestException;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.NewUtenteRespDTO;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.UtentePayloadDTO;
import dariocecchinato.s19l5_gestione_eventi_project.services.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/utenti")
public class UtentiController {
    @Autowired
    private UtentiService utentiService;

    @GetMapping
    public Page<Utente> findAllUtenti(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome")String sortby){
        return this.utentiService.findAll(page, size, sortby);
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
