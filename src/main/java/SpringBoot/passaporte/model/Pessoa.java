package SpringBoot.passaporte.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false)
    private LocalDateTime dataNascimento;
    @Column(nullable = false, unique = true)
    private String email;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "passaporte_id", unique = true)
    private Passaporte passaporte;
}
