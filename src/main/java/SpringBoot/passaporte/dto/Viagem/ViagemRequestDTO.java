package SpringBoot.passaporte.dto.Viagem;

import jakarta.persistence.Column;
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
public class ViagemRequestDTO {

    @NotBlank(message = "Destino é obrigatório")
    private String destino;

    @NotNull(message = "Data saída é obrigatório")
    private LocalDateTime dataSaida;

    private LocalDateTime dataRetorno;

    @NotNull(message = "ID da pessoa é obrigatório")
    @Positive(message = "ID da pessoa deve ser válido")
    private Long pessoaId;
}
