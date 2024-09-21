package dariocecchinato.s19l5_gestione_eventi_project.payloads;

import java.time.LocalDateTime;

public record ErrorPayloadDTO(String message, LocalDateTime errorTime) {
}
