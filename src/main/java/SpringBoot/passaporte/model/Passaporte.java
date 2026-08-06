package SpringBoot.passaporte.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "passaporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passaporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private int numero;
    @Column(nullable = false)
    private String paisEmissor;
    @Column(nullable = false)
    private LocalDateTime dataEmissao;
    @Column(nullable = false)
    private LocalDateTime dataValidade;
    @OneToOne(mappedBy = "passaporte", fetch = FetchType.LAZY)
    private Pessoa pessoa;
}
