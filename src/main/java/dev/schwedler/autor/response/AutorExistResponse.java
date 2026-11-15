package dev.schwedler.autor.response;

import lombok.Builder;

@Builder
public record AutorExistResponse(
        String message,
        boolean exist

) {
}
