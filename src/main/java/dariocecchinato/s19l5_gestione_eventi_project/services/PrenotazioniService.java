package dariocecchinato.s19l5_gestione_eventi_project.services;



import dariocecchinato.s19l5_gestione_eventi_project.entities.Evento;
import dariocecchinato.s19l5_gestione_eventi_project.entities.Prenotazione;
import dariocecchinato.s19l5_gestione_eventi_project.entities.Utente;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.NotFoundException;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.PrenotazionePayloadDTO;
import dariocecchinato.s19l5_gestione_eventi_project.repositories.PrenotazioniRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        //Dipendente dipendente = dipendentiReporitory.findById(body.dipendente_id()).orElseThrow(()->new NotFoundException(body.dipendente_id()));
        Evento evento = eventiService.trovaPerId(eventoId).orElseThrow(()-> new NotFoundException(eventoId));
        Utente utente= utentiService.findById(utenteId);

        Prenotazione prenotazione = new Prenotazione(dataPrenotazione, evento, utente);

            return prenotazioniRepository.save(prenotazione);
        }
    }




