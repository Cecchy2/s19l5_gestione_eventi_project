package dariocecchinato.s19l5_gestione_eventi_project.controllers;

import dariocecchinato.s19l5_gestione_eventi_project.exceptions.BadRequestException;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.EventoCreatoResponseDTO;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.EventoPayloadDTO;
import dariocecchinato.s19l5_gestione_eventi_project.services.EventiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventi")
public class EventiController {
    @Autowired
    private EventiService eventiService;


    @PostMapping("/organizzatori")
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public EventoCreatoResponseDTO creaEvento(@RequestBody EventoPayloadDTO body, BindingResult validationResult) {
        System.out.println("Payload ricevuto: " + body);
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Errori nel Payload. " + messages);
        } else {
            
            System.out.println("ID Organizzatore: " + body.organizzatoreId());
            return new EventoCreatoResponseDTO(this.eventiService.save(body).getId());
        }
    }

}
