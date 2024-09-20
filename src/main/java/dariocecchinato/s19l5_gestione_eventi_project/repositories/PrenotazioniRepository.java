package dariocecchinato.s19l5_gestione_eventi_project.repositories;

import dariocecchinato.s19l5_gestione_eventi_project.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazione, UUID> {
}
