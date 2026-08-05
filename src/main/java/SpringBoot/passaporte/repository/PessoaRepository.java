package SpringBoot.passaporte.repository;

import SpringBoot.passaporte.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByCpf(String cpf);

    Optional<Pessoa> findByEmail(String email);

    Optional<Pessoa> findByPassaporteNumero(String numero);
}
