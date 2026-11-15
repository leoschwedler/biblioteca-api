package dev.schwedler.autor.response;

import java.util.Set;

public interface AutorResumoLivrosProjection {
    Long getId();
    String getNome();
    Set<LivroItem> getLivros();      // lista da projection filha

    interface LivroItem {            // projection aninhada
        String getTitulo();
        Integer getAnoPublicacao();
    }
}