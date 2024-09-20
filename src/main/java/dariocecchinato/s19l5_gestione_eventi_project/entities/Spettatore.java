package dariocecchinato.s19l5_gestione_eventi_project.entities;

import dariocecchinato.s19l5_gestione_eventi_project.Enum.Ruolo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
public class Spettatore extends Utente{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID id;
    @OneToMany(mappedBy = "spettatore")
    private List<Prenotazione> prenotazioni= new ArrayList<>();

    public Spettatore(String nome, String cognome, String email, String password) {
        super(nome, cognome, email, password, Ruolo.SPETTATORE);
    }
}
