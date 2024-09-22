package dariocecchinato.s19l5_gestione_eventi_project.exceptions;

import java.util.UUID;

public class ResponseStatusException extends RuntimeException{
    public ResponseStatusException(UUID id) {
        super("Non sei autorizzato a modificare l'evento " + id);
    }

    public ResponseStatusException(String message) {
        super(message);
    }
}

