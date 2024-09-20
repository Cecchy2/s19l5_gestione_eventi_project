package dariocecchinato.s19l5_gestione_eventi_project.entities;

import dariocecchinato.s19l5_gestione_eventi_project.Enum.Ruolo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Organizzatore extends Utente{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID id;
    @OneToMany(mappedBy = "organizzatore")
    private List<Evento> eventi_organizzati;

    public Organizzatore(String nome, String cognome, String email, String password) {
        super(nome, cognome, email, password, Ruolo.ORGANIZZATORE);
    }


}
