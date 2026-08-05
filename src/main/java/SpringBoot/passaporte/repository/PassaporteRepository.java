package SpringBoot.passaporte.repository;

import SpringBoot.passaporte.model.Passaporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassaporteRepository extends JpaRepository<Passaporte, Long> {

    Optional<Passaporte> findByNumero(String numero);

    Optional<Passaporte> findByPessoaId(Long pessoaId);

    Optional<Passaporte> findByPessoaCpf(String cpf);
}
