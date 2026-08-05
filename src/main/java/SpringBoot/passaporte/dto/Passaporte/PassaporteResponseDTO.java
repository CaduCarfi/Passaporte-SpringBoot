package SpringBoot.passaporte.dto.Passaporte;

import SpringBoot.passaporte.dto.Pessoa.PessoaResponseDTO;
import SpringBoot.passaporte.model.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassaporteResponseDTO {

    private Long id;
    private int numero;
    private String paisEmissor;
    private LocalDateTime dataEmissao;
    private LocalDateTime dataValidade;
    private PessoaResponseDTO pessoa;
}
