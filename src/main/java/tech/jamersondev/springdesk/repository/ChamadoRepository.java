package tech.jamersondev.springdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.jamersondev.springdesk.model.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
