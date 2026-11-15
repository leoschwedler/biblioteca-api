package dev.schwedler.autor.response;

import dev.schwedler.livro.response.LivroResponse;
import dev.schwedler.livro.response.LivroResumoResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public record AutorResponse(
        Long id,
        String nome,
        String email,
        String nacionalidade,
        LocalDate dataNascimento,
        LocalDateTime criadoEm,
        Set<LivroResumoResponse> livros
) {
}
