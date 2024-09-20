package dariocecchinato.s19l5_gestione_eventi_project.payloads;

import java.time.LocalDate;

public record EventoPayloadDTO(
        String titolo,
        String descrizione,
        LocalDate data_evento,
        String luogo_evento,
        int numero_posti){}
