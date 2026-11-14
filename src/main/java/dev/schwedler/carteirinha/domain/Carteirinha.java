package dev.schwedler.carteirinha.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "carteirinha")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carteirinha {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "numero_carteirinha")
    private String numeroCarteirinha;
    @Column(name = "emitida_em")
    private LocalDateTime emitidaEm;
    @Column(name = "expira_em")
    private LocalDateTime expiraEm;
    private Boolean ativa;
}
