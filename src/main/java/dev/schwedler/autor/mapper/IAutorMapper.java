package dev.schwedler.autor.mapper;

import dev.schwedler.autor.domain.Autor;
import dev.schwedler.autor.request.AutorPostRequest;
import dev.schwedler.autor.response.AutorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IAutorMapper {


    Autor toDomain(AutorPostRequest autorPostRequest);

    AutorResponse toResponse(Autor autor);

    void updateDomain(AutorPostRequest autorPostRequest, @MappingTarget Autor autor);
}
