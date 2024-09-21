package dariocecchinato.s19l5_gestione_eventi_project.services;



import dariocecchinato.s19l5_gestione_eventi_project.repositories.PrenotazioniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioniService {
    @Autowired
    private PrenotazioniRepository prenotazioniRepository;
    @Autowired
    private EventiService eventiService;



}
