package dariocecchinato.s19l5_gestione_eventi_project.services;

import dariocecchinato.s19l5_gestione_eventi_project.entities.Organizzatore;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.BadRequestException;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.NotFoundException;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.OrganizzatorePayloadDTO;
import dariocecchinato.s19l5_gestione_eventi_project.repositories.OrganizzatoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrganizzatoriService {
    @Autowired
    private OrganizzatoriRepository organizzatoriRepository;
    @Autowired
    private PasswordEncoder bcrypt;

    public Organizzatore save(OrganizzatorePayloadDTO body){
        this.organizzatoriRepository.findByEmail(body.email()).ifPresent(
                organizzatore->{
                    throw new BadRequestException("L'email " + body.email() + " è già in uso!");
                }
        );
        Organizzatore newOrganizzatore = new Organizzatore(body.nome(), body.cognome(), body.email(), bcrypt.encode(body.password()));
        return this.organizzatoriRepository.save(newOrganizzatore);
    }

    public Organizzatore findById(UUID organizzatoreId){
        return this.organizzatoriRepository.findById(organizzatoreId).orElseThrow(()-> new NotFoundException(organizzatoreId));
    }

    public List<Organizzatore> findAll(){
        return this.organizzatoriRepository.findAll();
    }
}
