package dev.schwedler.livro.response;

import dev.schwedler.livro.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LivroResumoResponse(
        Long id,
        String titulo,
        Integer anoPublicacao,
        Integer paginas,
        BigDecimal preco,
        Status status,
        LocalDateTime criado_em
) {
}
