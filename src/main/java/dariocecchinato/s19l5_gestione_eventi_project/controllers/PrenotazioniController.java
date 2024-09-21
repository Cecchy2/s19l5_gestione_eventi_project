package dariocecchinato.s19l5_gestione_eventi_project.controllers;

import dariocecchinato.s19l5_gestione_eventi_project.entities.Prenotazione;
import dariocecchinato.s19l5_gestione_eventi_project.entities.Utente;
import dariocecchinato.s19l5_gestione_eventi_project.services.PrenotazioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
    @RequestMapping("/prenotazioni")
    public class PrenotazioniController {

        @Autowired
        private PrenotazioniService prenotazioniService;

        /*@PostMapping("/{eventoId}")
        public Prenotazione prenotaPosto(@PathVariable UUID eventoId, Authentication authentication) {
            Utente utente = (Utente) authentication.getPrincipal();
            return prenotazioniService.prenotaPosto(eventoId, utente);
        }

        @DeleteMapping("/{prenotazioneId}")
        public void cancellaPrenotazione(@PathVariable UUID prenotazioneId, Authentication authentication) {
            Utente utente = (Utente) authentication.getPrincipal();
            prenotazioniService.cancellaPrenotazione(prenotazioneId, utente);
        }*/
    }


