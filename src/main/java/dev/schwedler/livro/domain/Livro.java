package dev.schwedler.livro.domain;

import dev.schwedler.autor.domain.Autor;
import dev.schwedler.livro.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "livro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Livro {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String titulo;
    @Column(name = "ano_publicacao")
    private Integer anoPublicacao;
    private Integer paginas;
    private BigDecimal preco;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "criado_em", insertable = false, updatable = false)
    private LocalDateTime criadoEm;
    @ManyToMany(mappedBy = "livros", fetch = FetchType.LAZY)
    private Set<Autor> autores;
}
