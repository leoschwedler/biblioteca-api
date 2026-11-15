package dev.schwedler.autor.service;

import dev.schwedler.autor.domain.Autor;
import dev.schwedler.autor.mapper.IAutorMapper;
import dev.schwedler.autor.repository.IAutorRepository;
import dev.schwedler.autor.request.AutorPostRequest;
import dev.schwedler.autor.response.AutorExistResponse;
import dev.schwedler.autor.response.AutorQuantidadeProjection;
import dev.schwedler.autor.response.AutorResponse;
import dev.schwedler.autor.response.AutorResumoLivrosProjection;
import dev.schwedler.exception.BadRequestException;
import dev.schwedler.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutorSevice {

    private final IAutorRepository autorRepository;
    private final IAutorMapper mapper;

    //CREAT
    @Transactional
    public AutorResponse saveAutor(AutorPostRequest request){
        boolean existsByEmail = autorRepository.existsByEmail(request.email());
        if (existsByEmail){
            throw new BadRequestException("Ja existe um autor com esse email");
        }else {
            Autor autorDomain = autorRepository.save(mapper.toDomain(request));
            return mapper.toResponse(autorDomain);
        }
    }

    //READ
    @Transactional(readOnly = true)
    public AutorResponse getById(Long id){
        Autor autorDomain = autorRepository.findById(id).orElseThrow(() -> new NotFoundException("Autor com o id:" + id + " nao foi encontrado"));
        return mapper.toResponse(autorDomain);
    }

    @Transactional(readOnly = true)
    public AutorExistResponse getExistsByEmail(String email){
        boolean existsByEmail = autorRepository.existsByEmail(email);
        if (existsByEmail){
             AutorExistResponse autorExistResponse = AutorExistResponse.builder()
                    .message("Ja existe um autor com esse email")
                    .exist(true)
                    .build();
            return autorExistResponse;
        }else {
               AutorExistResponse autorExistResponse = AutorExistResponse.builder()
                    .message("Ainda nao existe um autor com esse email")
                    .exist(false)
                    .build();
            return autorExistResponse;
        }
    }

    @Transactional(readOnly = true)
    public List<AutorQuantidadeProjection> getQuantidadeLivrosPorAutor(){
       return autorRepository.getQuantidadeLivrosPorAutor();
    }

    @Transactional(readOnly = true)
    public AutorResumoLivrosProjection getResumoById(Long id){
        AutorResumoLivrosProjection autorResumoLivrosProjection = autorRepository.getResumoById(id).orElseThrow(() -> new NotFoundException("Autor com o id:" + id + " nao encontrado"));
        return autorResumoLivrosProjection;
    }

    @Transactional(readOnly = true)
    public Page<AutorResponse> getAutorPageble(Pageable pageable){
        return autorRepository.findAll(pageable).map(mapper::toResponse);
    }

    //UPDATE
    @Transactional
    public AutorResponse updateAutor(AutorPostRequest request, Long id){
        Autor autorDomain = autorRepository.findById(id).orElseThrow(() -> new NotFoundException("Autor com o id:" + id + " nao foi encontrado"));
        boolean existsByEmail = autorRepository.existsByEmailAndIdNot(request.email(), id);
        if (existsByEmail){
            throw new BadRequestException("Ja existe um autor com esse email");
        }else {
            mapper.updateDomain(request, autorDomain);
            autorRepository.save(autorDomain);
            return mapper.toResponse(autorDomain);
        }
    }

    //DELETE
    @Transactional
    public void deleteAutor(Long id){
        Autor autorDomain = autorRepository.findById(id).orElseThrow(() -> new NotFoundException("Autor com o id:" + id + " nao foi encontrado"));
        autorRepository.delete(autorDomain);
    }
}
