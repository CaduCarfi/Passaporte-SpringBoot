package SpringBoot.passaporte.dto.Passaporte;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassaporteRequestDTO {

    @Positive(message = "Número do passaporte deve ser maior que 0")
    private int numero;

    @NotBlank(message = "País emissor é obrigatório")
    private String paisEmissor;

    @NotNull(message = "Data de emissão é obrigatória")
    private LocalDateTime dataEmissao;

    @NotNull(message = "Data de validade é obrigatória")
    private LocalDateTime dataValidade;

    @NotNull(message = "ID da pessoa é obrigatório")
    @Positive(message = "ID da pessoa deve ser válido")
    private Long pessoaId;

}
