package SpringBoot.passaporte.dto.Pessoa;

import SpringBoot.passaporte.dto.Passaporte.PassaporteResponseDTO;
import SpringBoot.passaporte.dto.Viagem.ViagemResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    private LocalDateTime dataNascimento;
    private String email;
    private PassaporteResponseDTO passaporte;
}
