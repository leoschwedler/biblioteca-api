package dev.schwedler.autor.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record AutorPostRequest(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        String nacionalidade,
        LocalDate dataNascimento
) {
}
