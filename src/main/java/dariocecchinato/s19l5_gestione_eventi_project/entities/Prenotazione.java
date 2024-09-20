package dariocecchinato.s19l5_gestione_eventi_project.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID id;
    private LocalDate data_prenotazione;
    @ManyToOne
    @JoinColumn(name = "spettatore_id")
    private Spettatore spettatore;
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    public Prenotazione(LocalDate data_prenotazione, Spettatore spettatore, Evento evento) {
        this.data_prenotazione = data_prenotazione;
        this.spettatore = spettatore;
        this.evento = evento;
    }
}
