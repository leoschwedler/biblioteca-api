package dev.schwedler.emprestimo.domain;

import dev.schwedler.carteirinha.domain.Carteirinha;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "socio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Socio {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carteirinha")
    private Carteirinha carteirinha;
    private String nome;
    private String email;
    private String telefone;
    private LocalDateTime criado_em;
}
