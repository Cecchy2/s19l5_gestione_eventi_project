package dariocecchinato.s19l5_gestione_eventi_project.payloads;

import dariocecchinato.s19l5_gestione_eventi_project.entities.Evento;
import dariocecchinato.s19l5_gestione_eventi_project.entities.Utente;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record PrenotazionePayloadDTO(
        LocalDate data_prenotazione,
        @NotNull(message = "Devi inserire l'ID dell' evento")
        UUID eventoId,
        @NotNull(message = "Devi inserire l'ID dell' utente")
        UUID utenteId
) {
}
