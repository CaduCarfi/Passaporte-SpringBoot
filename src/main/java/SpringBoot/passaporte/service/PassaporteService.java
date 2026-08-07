package SpringBoot.passaporte.service;

import SpringBoot.passaporte.dto.Passaporte.PassaporteRequestDTO;
import SpringBoot.passaporte.dto.Passaporte.PassaporteResponseDTO;
import SpringBoot.passaporte.dto.Pessoa.PessoaResponseDTO;
import SpringBoot.passaporte.model.Passaporte;
import SpringBoot.passaporte.model.Pessoa;
import SpringBoot.passaporte.repository.PassaporteRepository;
import SpringBoot.passaporte.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassaporteService {

    private final PassaporteRepository passaporteRepository;
    private final PessoaRepository pessoaRepository;

    public PassaporteService(PassaporteRepository passaporteRepository, PessoaRepository pessoaRepository) {
        this.passaporteRepository = passaporteRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public PassaporteResponseDTO create(PassaporteRequestDTO dto) {
        if (passaporteRepository.findByNumero(String.valueOf(dto.getNumero())).isPresent()) {
            throw new RuntimeException("Número de passaporte já existe");
        }
        if (dto.getDataValidade().isBefore(dto.getDataEmissao()) ||
                dto.getDataValidade().isEqual(dto.getDataEmissao())) {
            throw new RuntimeException("Data de validade deve ser maior que a data de emissão");
        }

        Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
                .orElseThrow(() -> new RuntimeException("Pessoa não existe"));

        if (pessoa.getPassaporte() != null) {
            throw new RuntimeException("Pessoa já possui um passaporte");
        }

        Passaporte passaporte = new Passaporte();
        passaporte.setNumero(dto.getNumero());
        passaporte.setPaisEmissor(dto.getPaisEmissor());
        passaporte.setDataEmissao(dto.getDataEmissao());
        passaporte.setDataValidade(dto.getDataValidade());
        passaporte.setPessoa(pessoa);

        Passaporte salvo = passaporteRepository.save(passaporte);
        return toResponseDTO(salvo);
    }

    public List<PassaporteResponseDTO> findAll() {
        return passaporteRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public PassaporteResponseDTO findById(Long id) {
        Passaporte passaporte = passaporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passaporte não existe"));
        return toResponseDTO(passaporte);
    }

    public PassaporteResponseDTO atualizar(Long id, PassaporteRequestDTO dto) {
        Passaporte passaporte = passaporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passaporte não encontrado"));

        if (dto.getDataValidade().isBefore(dto.getDataEmissao()) ||
                dto.getDataValidade().isEqual(dto.getDataEmissao())) {
            throw new RuntimeException("Data de validade deve ser maior que a data de emissão");
        }

        passaporte.setNumero(dto.getNumero());
        passaporte.setPaisEmissor(dto.getPaisEmissor());
        passaporte.setDataEmissao(dto.getDataEmissao());
        passaporte.setDataValidade(dto.getDataValidade());

        Passaporte atualizado = passaporteRepository.save(passaporte);
        return toResponseDTO(atualizado);
    }

    public void deletar(Long id) {
        if (!passaporteRepository.existsById(id)) {
            throw new RuntimeException("Passaporte não existe");
        }
        passaporteRepository.deleteById(id);
    }

    public PassaporteResponseDTO toResponseDTO(Passaporte passaporte) {
        PessoaResponseDTO pessoaResponseDTO = null;
        if (passaporte.getPessoa() != null) {
            pessoaResponseDTO = toPessoaResponseDTO(passaporte.getPessoa());
        }
        return new PassaporteResponseDTO(
                passaporte.getId(),
                passaporte.getNumero(),
                passaporte.getPaisEmissor(),
                passaporte.getDataEmissao(),
                passaporte.getDataValidade(),
                pessoaResponseDTO
        );
    }

    private PessoaResponseDTO toPessoaResponseDTO(Pessoa pessoa) {
        return new PessoaResponseDTO(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getDataNascimento(),
                pessoa.getEmail(),
                null
        );
    }
}
