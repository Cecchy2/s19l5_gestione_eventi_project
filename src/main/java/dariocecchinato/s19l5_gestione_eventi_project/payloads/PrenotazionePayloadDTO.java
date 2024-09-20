package dariocecchinato.s19l5_gestione_eventi_project.payloads;

import dariocecchinato.s19l5_gestione_eventi_project.entities.Evento;
import dariocecchinato.s19l5_gestione_eventi_project.entities.Spettatore;

import java.time.LocalDate;

public record PrenotazionePayloadDTO(
        LocalDate data_prenotazione, Spettatore spettatore, Evento evento
) {}
