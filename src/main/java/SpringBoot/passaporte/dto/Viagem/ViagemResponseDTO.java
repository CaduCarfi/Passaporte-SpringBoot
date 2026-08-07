package SpringBoot.passaporte.dto.Viagem;

import SpringBoot.passaporte.dto.Pessoa.PessoaResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViagemResponseDTO {

    private Long id;
    private String destino;
    private LocalDateTime dataSaida;
    private LocalDateTime dataRetorno;
    private PessoaResponseDTO pessoa;
}
