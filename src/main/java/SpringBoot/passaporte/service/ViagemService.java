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

        if (dto.getDataSaida().isBefore(dto.getDataRetorno()) || dto.getDataSaida().isEqual(dto.getDataRetorno())) {
            throw new RuntimeException("Data de retorno deve ser maior que a data de saida");
        }

        Viagem viagem = new Viagem();
        viagem.setDestino(dto.getDestino());
        viagem.setDataSaida(dto.getDataSaida());
        viagem.setDataRetorno(dto.getDataRetorno());

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
        Viagem viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        viagem.setDestino(dto.getDestino());
        viagem.setDataSaida(dto.getDataSaida());
        viagem.setDataRetorno(dto.getDataRetorno());

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
            pessoaResponseDTO = toViagemResponseDTO(viagem.getPessoa());
        }
        return new ViagemResponseDTO(
                viagem.getId(),
                viagem.getDestino(),
                viagem.getDataSaida(),
                viagem.getDataRetorno(),
                pessoaResponseDTO
        );
    }

    public PessoaResponseDTO toViagemResponseDTO(Pessoa pessoa) {
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
