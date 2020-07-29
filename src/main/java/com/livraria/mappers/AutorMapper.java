package com.livraria.mappers;

import com.livraria.dto.AutorDTO;
import com.livraria.dto.ClienteDTO;
import com.livraria.entities.Autor;
import com.livraria.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    AutorMapper INSTANCE = Mappers.getMapper(AutorMapper.class);

    AutorDTO autorToAutorDTO(Autor autor);

    Autor autorDtoToAutor(AutorDTO autorDTO);

    List<AutorDTO> listAutorTOListAutorDTO(List<Autor> autor);


}
