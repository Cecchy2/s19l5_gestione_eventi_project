package dariocecchinato.s19l5_gestione_eventi_project.controllers;

import dariocecchinato.s19l5_gestione_eventi_project.repositories.OrganizzatoriRepository;
import dariocecchinato.s19l5_gestione_eventi_project.services.OrganizzatoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organizzatori")
public class OrganizzatoriController {
    @Autowired
    private OrganizzatoriService organizzatoriService;
}
