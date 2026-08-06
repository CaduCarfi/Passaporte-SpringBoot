package SpringBoot.passaporte.service;

import SpringBoot.passaporte.dto.Passaporte.PassaporteResponseDTO;
import SpringBoot.passaporte.dto.Pessoa.PessoaRequestDTO;
import SpringBoot.passaporte.dto.Pessoa.PessoaResponseDTO;
import SpringBoot.passaporte.model.Passaporte;
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

    public PessoaResponseDTO create(PessoaRequestDTO dto) {
        if (pessoaRepository.findByCpf(dto.getCpf()).isPresent()) {
            throw new RuntimeException("Cpf já existe");
        }
        if (pessoaRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email já existe");
        }

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

    public PessoaResponseDTO findById(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não existe"));
        return toResponseDTO(pessoa);
    }

    public PessoaResponseDTO atualizar(Long id, PessoaRequestDTO dto) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        if (!pessoa.getCpf().equals(dto.getCpf()) && pessoaRepository.findByCpf(dto.getCpf()).isPresent()) {
            throw new RuntimeException("CPF já existe");
        }

        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        pessoa.setEmail(dto.getEmail());

        Pessoa atualizado = pessoaRepository.save(pessoa);
        return toResponseDTO(atualizado);
    }

    public void deletar(Long id) {
        if (! pessoaRepository.existsById(id)) {
            throw new RuntimeException("Pessoa não existe");
        }
        pessoaRepository.deleteById(id);
    }

    public PessoaResponseDTO toResponseDTO(Pessoa pessoa) {
        PassaporteResponseDTO passaporteResponseDTO = null;
        if (pessoa.getPassaporte() != null) {
            passaporteResponseDTO = toPassaporteResponseDTO(pessoa.getPassaporte());
        }
        return new PessoaResponseDTO(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getDataNascimento(),
                pessoa.getEmail(),
                passaporteResponseDTO
        );
    }

    private PassaporteResponseDTO toPassaporteResponseDTO(Passaporte passaporte){
        return new PassaporteResponseDTO(
                passaporte.getId(),
                passaporte.getNumero(),
                passaporte.getPaisEmissor(),
                passaporte.getDataEmissao(),
                passaporte.getDataValidade(),
                null
        );
    }

}
