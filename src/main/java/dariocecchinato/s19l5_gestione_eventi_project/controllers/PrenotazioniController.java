package dariocecchinato.s19l5_gestione_eventi_project.controllers;

import dariocecchinato.s19l5_gestione_eventi_project.entities.Prenotazione;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.BadRequestException;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.PrenotazionePayloadDTO;
import dariocecchinato.s19l5_gestione_eventi_project.services.EventiService;
import dariocecchinato.s19l5_gestione_eventi_project.services.PrenotazioniService;
import dariocecchinato.s19l5_gestione_eventi_project.services.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {
    @Autowired
    private PrenotazioniService prenotazioniService;
    @Autowired
    private UtentiService utentiService;
    @Autowired
    private EventiService eventiService;

    @PostMapping("/spettatori")
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione savePrenotazione(@RequestBody @Validated PrenotazionePayloadDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()){
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(" ."));
            throw new BadRequestException("Errore nel payload " + message);
        }else{
            return this.prenotazioniService.creaPrenotazione(body);
        }
    }
}
