package dariocecchinato.s19l5_gestione_eventi_project.services;

import dariocecchinato.s19l5_gestione_eventi_project.entities.Evento;
import dariocecchinato.s19l5_gestione_eventi_project.entities.Prenotazione;
import dariocecchinato.s19l5_gestione_eventi_project.entities.Utente;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.BadRequestException;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.UnauthorizedException;
import dariocecchinato.s19l5_gestione_eventi_project.repositories.PrenotazioniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PrenotazioniService {
    @Autowired
    private PrenotazioniRepository prenotazioniRepository;
    @Autowired
    private EventiService eventiService;

    public Prenotazione prenotaPosto(UUID eventoId, Utente utente) {
        Optional<Evento> evento = eventiService.trovaPerId(eventoId);
        if (evento.isPresent() && evento.get().getNumero_posti() > 0) {
            Prenotazione prenotazione = new Prenotazione();
            prenotazione.setEvento(evento.get());
            prenotazione.setUtente(utente);
            prenotazione.setData_prenotazione(LocalDate.now());
            evento.get().setNumero_posti(evento.get().getNumero_posti()-1);
            eventiService.save(evento.get(), evento.get().getOrganizzatore());
            return prenotazioniRepository.save(prenotazione);
        } else {
            throw new BadRequestException("Non è stato possibile prenotare il posto");
        }

    }
    public void cancellaPrenotazione(UUID prenotazioneId, Utente utente) {
        Optional<Prenotazione> prenotazione = prenotazioniRepository.findById(prenotazioneId);
        if (prenotazione.isPresent() && prenotazione.get().getUtente().equals(utente)) {
            Evento evento = prenotazione.get().getEvento();
            evento.setNumero_posti(evento.getNumero_posti() + 1);
            eventiService.save(evento, evento.getOrganizzatore());
            prenotazioniRepository.delete(prenotazione.get());
        } else {
            throw new UnauthorizedException("Errore durante la cancellazione");
        }}

}
