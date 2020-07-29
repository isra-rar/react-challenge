package com.livraria.mappers;

import com.livraria.dto.AutorDTO;
import com.livraria.entities.Autor;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-29T05:32:45-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_252 (Private Build)"
)
@Component
public class AutorMapperImpl implements AutorMapper {

    @Override
    public AutorDTO autorToAutorDTO(Autor autor) {
        if ( autor == null ) {
            return null;
        }

        AutorDTO autorDTO = new AutorDTO();

        autorDTO.setId( autor.getId() );
        autorDTO.setNome( autor.getNome() );

        return autorDTO;
    }

    @Override
    public Autor autorDtoToAutor(AutorDTO autorDTO) {
        if ( autorDTO == null ) {
            return null;
        }

        Autor autor = new Autor();

        autor.setId( autorDTO.getId() );
        autor.setNome( autorDTO.getNome() );

        return autor;
    }

    @Override
    public List<AutorDTO> listAutorTOListAutorDTO(List<Autor> autor) {
        if ( autor == null ) {
            return null;
        }

        List<AutorDTO> list = new ArrayList<AutorDTO>( autor.size() );
        for ( Autor autor1 : autor ) {
            list.add( autorToAutorDTO( autor1 ) );
        }

        return list;
    }
}
