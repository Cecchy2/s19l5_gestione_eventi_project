package dariocecchinato.s19l5_gestione_eventi_project.payloads;



import dariocecchinato.s19l5_gestione_eventi_project.Enum.Ruolo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UtentePayloadDTO(
        @NotEmpty(message = "Devi inserire un nome")
        @Size(min = 3, max = 20, message = "Il nome deve avere dai 3 ai 20 caratteri")
        String nome,
        @NotEmpty(message = "Devi inserire un cognome")
        @Size(min = 3, max = 20, message = "Il nome deve avere dai 3 ai 20 caratteri")
        String cognome,
        @NotEmpty(message = "Devi inserire una email")
        @Email(message = "Devi inserire una email valida")
        String email,
        @NotEmpty(message = "Devi inserire una email")
        String password,
        @NotNull(message = "sei un ORGANIZZATORE o uno SPETTATORE?")
        Ruolo ruolo) {
}
