package dariocecchinato.s19l5_gestione_eventi_project.controllers;


import dariocecchinato.s19l5_gestione_eventi_project.entities.Evento;
import dariocecchinato.s19l5_gestione_eventi_project.entities.Utente;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.BadRequestException;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.NotFoundException;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.EventoCreatoResponseDTO;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.EventoPayloadDTO;
import dariocecchinato.s19l5_gestione_eventi_project.services.EventiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
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

    @GetMapping
    public Page<Evento> getAllEventi (@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String sortBy){
        return  this.eventiService.findAll(page, size, sortBy);
    }

    @GetMapping("/{eventoId}")
    public Evento findById(@PathVariable UUID eventoId){
        if (eventoId == null) throw new BadRequestException("Devi inserire un id ");
        return this.eventiService.trovaPerId(eventoId ).orElseThrow(()->new NotFoundException(eventoId));
    }

    @PutMapping("/{eventoId}")
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public Evento findByIdAndUpdate(@PathVariable UUID eventoId, @RequestBody EventoPayloadDTO body){
        return this.eventiService.findByIdAndUpdate(eventoId, body);
    }

    @DeleteMapping("/{eventoId}")
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID eventoId){
        Utente organizzatore = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        eventiService.findByIdAndDelete(eventoId,organizzatore);
    }


}
