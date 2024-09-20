package dariocecchinato.s19l5_gestione_eventi_project.controllers;


import dariocecchinato.s19l5_gestione_eventi_project.entities.Evento;
import dariocecchinato.s19l5_gestione_eventi_project.entities.Utente;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.BadRequestException;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.EventoCreatoResponseDTO;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.EventoPayloadDTO;
import dariocecchinato.s19l5_gestione_eventi_project.services.EventiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventi")
public class EventiController {
    @Autowired
    private EventiService eventiService;


    /*@GetMapping
    public List<Evento> listaEventi() {
        return eventiService.trovaEventiFuturi();
    }*/

    @PostMapping("/organizzatori")
    public Evento creaEvento(@RequestBody Evento evento, Authentication authentication) {
        Utente organizzatore = (Utente) authentication;
        return eventiService.save(evento, organizzatore);
    }
}
