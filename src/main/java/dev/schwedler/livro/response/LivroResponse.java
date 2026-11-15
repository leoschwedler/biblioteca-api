package dev.schwedler.livro.response;

import dev.schwedler.autor.domain.Autor;
import dev.schwedler.autor.response.AutorResponse;
import dev.schwedler.autor.response.AutorResumoResponse;
import dev.schwedler.livro.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record LivroResponse(
        Long id,
        String titulo,
        Integer anoPublicacao,
        Integer paginas,
        BigDecimal preco,
        Status status,
        LocalDateTime criado_em,
        Set<AutorResumoResponse> autores
) {
}
