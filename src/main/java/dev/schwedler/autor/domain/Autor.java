package dev.schwedler.autor.domain;

import dev.schwedler.livro.domain.Livro;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "autor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Autor {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String nome;
    private String email;
    private String nacionalidade;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    @Column(name = "criado_em", insertable = false, updatable = false)
    private LocalDateTime criadoEm;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "autor_livro",
            joinColumns = @JoinColumn(name = "id_autor"),
            inverseJoinColumns = @JoinColumn(name = "id_livro")
    )
    private Set<Livro> livros;
}
