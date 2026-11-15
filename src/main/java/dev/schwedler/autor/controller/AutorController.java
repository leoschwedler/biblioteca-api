package dev.schwedler.autor.controller;

import dev.schwedler.autor.request.AutorPostRequest;
import dev.schwedler.autor.response.AutorExistResponse;
import dev.schwedler.autor.response.AutorQuantidadeProjection;
import dev.schwedler.autor.response.AutorResponse;
import dev.schwedler.autor.response.AutorResumoLivrosProjection;
import dev.schwedler.autor.service.AutorSevice;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/autores")
@RequiredArgsConstructor
public class AutorController {

    private final AutorSevice service;


    @PostMapping()
    public ResponseEntity<AutorResponse> saveAutor(@Valid @RequestBody AutorPostRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAutor(request));

    }

    @GetMapping
    public ResponseEntity<Page<AutorResponse>> getAutorPageble(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(service.getAutorPageble(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/exists")
    public ResponseEntity<AutorExistResponse> getById(@RequestParam(required = false) String email) {
        return ResponseEntity.ok(service.getExistsByEmail(email));
    }

    @GetMapping("/quantidade-livros")
    public ResponseEntity<List<AutorQuantidadeProjection>> getQuantidadeLivrosPorAutor() {
        return ResponseEntity.ok(service.getQuantidadeLivrosPorAutor());
    }

    @GetMapping("/resumo-livros/{id}")
    public ResponseEntity<AutorResumoLivrosProjection> getResumoById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getResumoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponse> updateAutor(@Valid @RequestBody AutorPostRequest request,@PathVariable Long id){
      return  ResponseEntity.ok(service.updateAutor(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutor(@PathVariable Long id){
        service.deleteAutor(id);
        return ResponseEntity.noContent().build();
    }

}
