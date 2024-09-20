package dariocecchinato.s19l5_gestione_eventi_project.repositories;

import dariocecchinato.s19l5_gestione_eventi_project.entities.Organizzatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrganizzatoriRepository extends JpaRepository<Organizzatore, UUID> {
Optional<Organizzatore> findByEmail(String email);

}
