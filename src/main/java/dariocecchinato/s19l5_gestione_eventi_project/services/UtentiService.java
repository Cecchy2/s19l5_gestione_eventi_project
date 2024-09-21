package dariocecchinato.s19l5_gestione_eventi_project.services;



import dariocecchinato.s19l5_gestione_eventi_project.entities.Utente;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.BadRequestException;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.NotFoundException;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.UtentePayloadDTO;
import dariocecchinato.s19l5_gestione_eventi_project.repositories.UtentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtentiService {
    @Autowired
    private UtentiRepository utentiRepository;
    @Autowired
    private PasswordEncoder bcrypt;

    public Utente registraUtente(UtentePayloadDTO body) {
        this.utentiRepository.findByEmail(body.email()).ifPresent(
                utente->{
                    throw new BadRequestException("L'email " + body.email() + " è già in uso!");
                }
        );
        Utente newUtente= new Utente(body.nome(),body.cognome(), body.email(), bcrypt.encode(body.password()), body.ruolo());
        return this.utentiRepository.save(newUtente);
    }

    public Utente findById(UUID userId) {
        return this.utentiRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public Page<Utente> findAll(int page, int size, String sortby){
        if (page > 10) page = 10;
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortby));
        return utentiRepository.findAll(pageable);
    }

    public Utente findUtenteByEmail(String email){
        return this.utentiRepository.findByEmail(email).orElseThrow(()->new NotFoundException("L' Email" + email + "non è stata trovata"));
    }


}
