package dariocecchinato.s19l5_gestione_eventi_project.services;

import dariocecchinato.s19l5_gestione_eventi_project.repositories.OrganizzatoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizzatoriService {
    @Autowired
    private OrganizzatoriRepository organizzatoriRepository;
}
