package dariocecchinato.s19l5_gestione_eventi_project.services;

import dariocecchinato.s19l5_gestione_eventi_project.entities.Evento;
import dariocecchinato.s19l5_gestione_eventi_project.entities.Organizzatore;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.BadRequestException;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.EventoPayloadDTO;
import dariocecchinato.s19l5_gestione_eventi_project.repositories.EventiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventiService {
    @Autowired
    private EventiRepository eventiRepository;
    @Autowired
    private OrganizzatoriService organizzatoriService;


    public Evento save (EventoPayloadDTO body){
        Organizzatore organizzatore = organizzatoriService.findById(body.organizzatoreId());
        Evento newEvento = new Evento(body.titolo(), body.descrizione(), body.data_evento(), body.luogo_evento(), body.numero_posti(), organizzatore);
        return eventiRepository.save(newEvento);
    }
}
