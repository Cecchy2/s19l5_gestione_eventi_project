package dariocecchinato.s19l5_gestione_eventi_project.services;

import dariocecchinato.s19l5_gestione_eventi_project.entities.Spettatore;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.BadRequestException;
import dariocecchinato.s19l5_gestione_eventi_project.exceptions.NotFoundException;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.SpettatorePayloadDTO;
import dariocecchinato.s19l5_gestione_eventi_project.repositories.SpettatoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SpettatoriService {
    @Autowired
    private SpettatoriRepository spettatoriRepository;
    @Autowired
    private PasswordEncoder bcrypt;

    public Spettatore save(SpettatorePayloadDTO body){
        this.spettatoriRepository.findByEmail(body.email()).ifPresent(
                spettatore->{
                    throw new BadRequestException("L'email " + body.email() + " è già in uso!");
                }
        );
        Spettatore newSpettatore = new Spettatore(body.nome(), body.cognome(), body.email(), bcrypt.encode(body.password()));
        return this.spettatoriRepository.save(newSpettatore);
    }
    public Spettatore findById(UUID organizzatoreId){
        return this.spettatoriRepository.findById(organizzatoreId).orElseThrow(()-> new NotFoundException(organizzatoreId));
    }

    public List<Spettatore> findAll(){
        return this.spettatoriRepository.findAll();
    }
}