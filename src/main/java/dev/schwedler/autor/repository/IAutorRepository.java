package dev.schwedler.autor.repository;

import dev.schwedler.autor.domain.Autor;
import dev.schwedler.autor.response.AutorQuantidadeProjection;
import dev.schwedler.autor.response.AutorResponse;
import dev.schwedler.autor.response.AutorResumoLivrosProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAutorRepository extends JpaRepository<Autor, Long> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    @EntityGraph(attributePaths = "livros")
    Optional<AutorResumoLivrosProjection> getResumoById(Long id);

    @Query(value = """
        SELECT autor.id,
               autor.nome,
               COUNT(autor_livro.id_livro) AS quantidade
        FROM autor
        LEFT JOIN autor_livro ON autor.id = autor_livro.id_autor
        GROUP BY autor.id, autor.nome
        ORDER BY autor.nome
        """,
            nativeQuery = true)
    List<AutorQuantidadeProjection> getQuantidadeLivrosPorAutor();





}
