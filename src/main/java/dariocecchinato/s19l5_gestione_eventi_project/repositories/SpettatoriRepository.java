package dariocecchinato.s19l5_gestione_eventi_project.repositories;

import dariocecchinato.s19l5_gestione_eventi_project.entities.Organizzatore;
import dariocecchinato.s19l5_gestione_eventi_project.entities.Spettatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpettatoriRepository extends JpaRepository<Spettatore, UUID> {
    Optional<Spettatore> findByEmail(String email);
}
