package SpringBoot.passaporte.repository;

import SpringBoot.passaporte.model.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {

    @Query("SELECT v.destino FROM Viagem v GROUP BY v.destino ORDER BY COUNT(v) DESC")
    List<String> findDestinosOrdenadosPorFrequencia();

}
