package SpringBoot.passaporte.repository;

import SpringBoot.passaporte.model.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {
}
