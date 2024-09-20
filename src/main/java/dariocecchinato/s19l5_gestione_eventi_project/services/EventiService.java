package dariocecchinato.s19l5_gestione_eventi_project.services;

import dariocecchinato.s19l5_gestione_eventi_project.entities.Evento;

import dariocecchinato.s19l5_gestione_eventi_project.entities.Utente;

import dariocecchinato.s19l5_gestione_eventi_project.exceptions.UnauthorizedException;

import dariocecchinato.s19l5_gestione_eventi_project.repositories.EventiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventiService {
    @Autowired
    private EventiRepository eventiRepository;



    public Evento save (Evento evento, Utente organizzatore){
        evento.setOrganizzatore(organizzatore);
        evento.setNumero_posti(evento.getNumero_posti());
        return eventiRepository.save(evento);
    }

    public Optional<Evento> trovaPerId(UUID id) {
        return eventiRepository.findById(id);
    }

    public void eliminaEvento(UUID id, Utente organizzatore) {
        Optional<Evento> evento = eventiRepository.findById(id);
        if (evento.isPresent() && evento.get().getOrganizzatore().equals(organizzatore)) {
            eventiRepository.delete(evento.get());
        } else {
            throw new UnauthorizedException("Errore nella rimozione");
        }
    }
}
