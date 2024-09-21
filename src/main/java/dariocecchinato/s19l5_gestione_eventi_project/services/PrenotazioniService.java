package dariocecchinato.s19l5_gestione_eventi_project.services;



import dariocecchinato.s19l5_gestione_eventi_project.entities.Evento;
import dariocecchinato.s19l5_gestione_eventi_project.entities.Prenotazione;
import dariocecchinato.s19l5_gestione_eventi_project.entities.Utente;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.BadRequestException;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.NotFoundException;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.PrenotazionePayloadDTO;
import dariocecchinato.s19l5_gestione_eventi_project.repositories.PrenotazioniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class PrenotazioniService {
    @Autowired
    private PrenotazioniRepository prenotazioniRepository;
    @Autowired
    private EventiService eventiService;
    @Autowired
    private UtentiService utentiService;

    public Prenotazione creaPrenotazione(PrenotazionePayloadDTO body){
        LocalDate dataPrenotazione= LocalDate.now();
        UUID eventoId = body.eventoId();
        UUID utenteId = body.utenteId();

        /*if (prenotazioniRepository.existsByDipendenteIdAndViaggioDataViaggio(dipendenteId,dataViaggio)){
            throw new BadRequestException("Il dipendente " + body.dipendente_id() + " ha già un viaggio prenotato per quella data");
        }else{*/
        Evento evento = eventiService.trovaPerId(eventoId).orElseThrow(()-> new NotFoundException(eventoId));
        Utente utente= utentiService.findById(utenteId);

        Prenotazione prenotazione = new Prenotazione(dataPrenotazione, evento, utente);

            return prenotazioniRepository.save(prenotazione);
        }

    public Page<Prenotazione> findAll (int page, int size, String sortby){
        if (page > 10) page =10;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortby));
        return this.prenotazioniRepository.findAll(pageable);
    }

    public Prenotazione findById (UUID viaggioId){
        if (viaggioId == null) throw new BadRequestException("Devi inserire un id ");
        return prenotazioniRepository.findById(viaggioId).orElseThrow(()-> new NotFoundException(viaggioId));
    }
    }




