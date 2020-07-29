package com.livraria.mappers;

import com.livraria.dto.AutorDTO;
import com.livraria.dto.LivroDTO;
import com.livraria.entities.Autor;
import com.livraria.entities.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    LivroMapper INSTANCE = Mappers.getMapper(LivroMapper.class);

    LivroDTO livroToLivroDTO(Livro livro);

    Livro livroDtoToLivro(LivroDTO livroDTO);

    List<LivroDTO> listLivroTOListLivroDTO(List<Livro> livros);


}
