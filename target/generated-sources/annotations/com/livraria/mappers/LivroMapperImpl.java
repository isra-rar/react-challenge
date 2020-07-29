package com.livraria.mappers;

import com.livraria.dto.AutorDTO;
import com.livraria.dto.LivroDTO;
import com.livraria.entities.Autor;
import com.livraria.entities.Livro;
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
public class LivroMapperImpl implements LivroMapper {

    @Override
    public LivroDTO livroToLivroDTO(Livro livro) {
        if ( livro == null ) {
            return null;
        }

        LivroDTO livroDTO = new LivroDTO();

        livroDTO.setId( livro.getId() );
        livroDTO.setTitulo( livro.getTitulo() );
        livroDTO.setCategoria( livro.getCategoria() );
        livroDTO.setValor( livro.getValor() );
        livroDTO.setQuantidade( livro.getQuantidade() );
        livroDTO.setAutor( autorToAutorDTO( livro.getAutor() ) );

        return livroDTO;
    }

    @Override
    public Livro livroDtoToLivro(LivroDTO livroDTO) {
        if ( livroDTO == null ) {
            return null;
        }

        Livro livro = new Livro();

        livro.setId( livroDTO.getId() );
        livro.setTitulo( livroDTO.getTitulo() );
        livro.setCategoria( livroDTO.getCategoria() );
        livro.setValor( livroDTO.getValor() );
        livro.setQuantidade( livroDTO.getQuantidade() );
        livro.setAutor( autorDTOToAutor( livroDTO.getAutor() ) );

        return livro;
    }

    @Override
    public List<LivroDTO> listLivroTOListLivroDTO(List<Livro> livros) {
        if ( livros == null ) {
            return null;
        }

        List<LivroDTO> list = new ArrayList<LivroDTO>( livros.size() );
        for ( Livro livro : livros ) {
            list.add( livroToLivroDTO( livro ) );
        }

        return list;
    }

    protected AutorDTO autorToAutorDTO(Autor autor) {
        if ( autor == null ) {
            return null;
        }

        AutorDTO autorDTO = new AutorDTO();

        autorDTO.setId( autor.getId() );
        autorDTO.setNome( autor.getNome() );

        return autorDTO;
    }

    protected Autor autorDTOToAutor(AutorDTO autorDTO) {
        if ( autorDTO == null ) {
            return null;
        }

        Autor autor = new Autor();

        autor.setId( autorDTO.getId() );
        autor.setNome( autorDTO.getNome() );

        return autor;
    }
}
