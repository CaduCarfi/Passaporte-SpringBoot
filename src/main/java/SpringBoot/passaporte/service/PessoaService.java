package SpringBoot.passaporte.service;

import SpringBoot.passaporte.dto.Pessoa.PessoaRequestDTO;
import SpringBoot.passaporte.dto.Pessoa.PessoaResponseDTO;
import SpringBoot.passaporte.model.Pessoa;
import SpringBoot.passaporte.repository.PassaporteRepository;
import SpringBoot.passaporte.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PassaporteRepository passaporteRepository;

    public PessoaService(PessoaRepository pessoaRepository, PassaporteRepository passaporteRepository) {
        this.pessoaRepository = pessoaRepository;
        this.passaporteRepository = passaporteRepository;
    }

    public Optional<PessoaResponseDTO> create(PessoaRequestDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        pessoa.setDataNascimento(dto.getDataNascimento());
        pessoa.setEmail(dto.getEmail());

        Pessoa salvo = pessoaRepository.save(pessoa);

        return toResponseDTO(salvo);
    }

    public List<PessoaResponseDTO> findAll() {
        return pessoaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }



    public PessoaResponseDTO toResponseDTO(Pessoa pessoa) {
        return new PessoaResponseDTO(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getDataNascimento(),
                pessoa.getEmail(),
                pessoa.getPassaporte()
        );
    }


}
