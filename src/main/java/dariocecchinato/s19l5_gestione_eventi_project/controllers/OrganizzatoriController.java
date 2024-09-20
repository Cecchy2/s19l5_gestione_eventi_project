package dariocecchinato.s19l5_gestione_eventi_project.controllers;

import dariocecchinato.s19l5_gestione_eventi_project.exceptions.BadRequestException;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.NewUtenteRespDTO;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.OrganizzatorePayloadDTO;
import dariocecchinato.s19l5_gestione_eventi_project.payloads.SpettatorePayloadDTO;
import dariocecchinato.s19l5_gestione_eventi_project.services.OrganizzatoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/organizzatori")
public class OrganizzatoriController {
    @Autowired
    private OrganizzatoriService organizzatoriService;




}
