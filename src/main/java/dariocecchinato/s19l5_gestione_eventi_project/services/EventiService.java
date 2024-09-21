package dariocecchinato.s19l5_gestione_eventi_project.services;



import dariocecchinato.s19l5_gestione_eventi_project.entities.Evento;
import dariocecchinato.s19l5_gestione_eventi_project.entities.Prenotazione;
import dariocecchinato.s19l5_gestione_eventi_project.entities.Utente;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.NotFoundException;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.UnauthorizedException;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.EventoPayloadDTO;
import dariocecchinato.s19l5_gestione_eventi_project.repositories.EventiRepository;
import dariocecchinato.s19l5_gestione_eventi_project.repositories.PrenotazioniRepository;
import dariocecchinato.s19l5_gestione_eventi_project.repositories.UtentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventiService {
    @Autowired
    private EventiRepository eventiRepository;
    @Autowired
    private UtentiRepository utentiRepository;
    @Autowired
    private PrenotazioniRepository prenotazioniRepository;



    public Evento save (EventoPayloadDTO body){
        UUID utenteId= body.organizzatoreId();
        Utente utente = utentiRepository.findById(body.organizzatoreId()).orElseThrow(()->new NotFoundException(body.organizzatoreId()));
        Evento evento = new Evento(body.titolo(), body.descrizione(), body.data_evento(), body.luogo_evento(), body.numero_posti(),utente);
        return eventiRepository.save(evento);
    }

    public Page<Evento> findAll(int page, int size, String sortby){
        if (page > 10) page = 10;
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortby));
        return eventiRepository.findAll(pageable);
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

    public Evento findByIdAndUpdate (UUID eventoId, EventoPayloadDTO body){

        Utente organizzatore= this.utentiRepository.findById(body.organizzatoreId()).orElseThrow(()->new NotFoundException(eventoId));
        Evento found = this.eventiRepository.findById(eventoId).orElseThrow(()->new NotFoundException(eventoId));
        found.setTitolo(body.titolo());
        found.setDescrizione(body.descrizione());
        found.setData_evento(body.data_evento());
        found.setLuogo_evento(body.luogo_evento());
        found.setNumero_posti(body.numero_posti());
        found.setOrganizzatore(organizzatore);
        return found;
    }

    public void findByIdAndDelete(UUID eventoId){
        List<Prenotazione> prenotazioniPerEvento= this.prenotazioniRepository.findByEvento_Id(eventoId);
        if (!prenotazioniPerEvento.isEmpty()){
            this.prenotazioniRepository.deleteAll(prenotazioniPerEvento);
        }
        Evento found = this.eventiRepository.findById(eventoId).orElseThrow(()->new NotFoundException(eventoId));
        eventiRepository.delete(found);
    }
}
