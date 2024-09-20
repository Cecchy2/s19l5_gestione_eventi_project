package dariocecchinato.s19l5_gestione_eventi_project.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String titolo;
    private String descrizione;
    private LocalDate data_evento;
    private String luogo_evento;
    private int numero_posti;
    @OneToMany(mappedBy = "evento")
    private List<Prenotazione> prenotazioni;
    @ManyToOne
    @JoinColumn(name = "organizzatore_id")
    private Organizzatore organizzatore;

    public Evento(String titolo, String descrizione, LocalDate data_evento, String luogo_evento, int numero_posti, List<Prenotazione> prenotazioni, Organizzatore organizzatore) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data_evento = data_evento;
        this.luogo_evento = luogo_evento;
        this.numero_posti = numero_posti;
        this.prenotazioni = prenotazioni;
        this.organizzatore = organizzatore;
    }
}
