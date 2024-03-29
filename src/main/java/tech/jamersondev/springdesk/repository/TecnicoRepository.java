package tech.jamersondev.springdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.jamersondev.springdesk.model.Tecnico;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
