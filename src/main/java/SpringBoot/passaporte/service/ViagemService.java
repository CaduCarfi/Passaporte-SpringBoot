package SpringBoot.passaporte.service;

import SpringBoot.passaporte.dto.Pessoa.PessoaResponseDTO;
import SpringBoot.passaporte.dto.Viagem.ViagemRequestDTO;
import SpringBoot.passaporte.dto.Viagem.ViagemResponseDTO;
import SpringBoot.passaporte.model.Pessoa;
import SpringBoot.passaporte.model.Viagem;
import SpringBoot.passaporte.repository.PessoaRepository;
import SpringBoot.passaporte.repository.ViagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViagemService {

    private final ViagemRepository viagemRepository;
    private final PessoaRepository pessoaRepository;

    public ViagemService(ViagemRepository viagemRepository, PessoaRepository pessoaRepository) {
        this.viagemRepository = viagemRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public ViagemResponseDTO create(ViagemRequestDTO dto) {

        if (dto.getDataRetorno() != null && dto.getDataRetorno().isBefore(dto.getDataSaida())) {
            throw new RuntimeException("Data de retorno não pode ser anterior à data de saída");
        }

        Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
                .orElseThrow(() -> new RuntimeException("Pessoa não existe"));

        Viagem viagem = new Viagem();
        viagem.setDestino(dto.getDestino());
        viagem.setDataSaida(dto.getDataSaida());
        viagem.setDataRetorno(dto.getDataRetorno());
        viagem.setPessoa(pessoa);

        Viagem salvo = viagemRepository.save(viagem);
        return toResponseDTO(salvo);
    }

    public List<ViagemResponseDTO> findAll() {
        return viagemRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ViagemResponseDTO findById(Long id) {
        Viagem viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        return toResponseDTO(viagem);
    }

    public ViagemResponseDTO atualizar(Long id, ViagemRequestDTO dto) {
        if (dto.getDataRetorno() != null && dto.getDataRetorno().isBefore(dto.getDataSaida())) {
            throw new RuntimeException("Data de retorno não pode ser anterior à data de saída");
        }
        Viagem viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));
        Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
                .orElseThrow(() -> new RuntimeException("Pessoa não existe"));

        viagem.setDestino(dto.getDestino());
        viagem.setDataSaida(dto.getDataSaida());
        viagem.setDataRetorno(dto.getDataRetorno());
        viagem.setPessoa(pessoa);

        Viagem atualizado = viagemRepository.save(viagem);
        return toResponseDTO(atualizado);
    }

    public void deletar(Long id) {
        if (! viagemRepository.existsById(id)){
            throw new RuntimeException("Viagem não existe");
        }
        viagemRepository.deleteById(id);
    }

    public ViagemResponseDTO toResponseDTO(Viagem viagem) {
        PessoaResponseDTO pessoaResponseDTO = null;
        if (viagem.getPessoa() != null) {
            pessoaResponseDTO = toPessoaResponseDTO(viagem.getPessoa());
        }
        return new ViagemResponseDTO(
                viagem.getId(),
                viagem.getDestino(),
                viagem.getDataSaida(),
                viagem.getDataRetorno(),
                pessoaResponseDTO
        );
    }

    public PessoaResponseDTO toPessoaResponseDTO(Pessoa pessoa) {
        return new PessoaResponseDTO(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getDataNascimento(),
                pessoa.getEmail(),
                null
        );
    }

    public String destinoMaisVisitado() {
        List<String> destinos = viagemRepository.findDestinosOrdenadosPorFrequencia();

        if (destinos.isEmpty()) {
            throw new RuntimeException("Nenhuma viagem cadastrada");
        }

        return destinos.get(0);  // pega o primeiro (mais visitado)
    }
}
