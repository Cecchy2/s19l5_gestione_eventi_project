package dariocecchinato.s19l5_gestione_eventi_project.repositories;


import dariocecchinato.s19l5_gestione_eventi_project.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UtentiRepository extends JpaRepository<Utente, UUID> {
}
