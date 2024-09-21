package dariocecchinato.s19l5_gestione_eventi_project.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID id;
    private LocalDate data_prenotazione;
    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    public Prenotazione(LocalDate data_prenotazione, Evento evento, Utente utente) {
        this.data_prenotazione = data_prenotazione;
        this.evento = evento;
        this.utente = utente;
    }
}
