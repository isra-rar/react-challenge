package com.livraria.mappers;

import com.livraria.dto.EnderecoDTO;
import com.livraria.entities.Endereco;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-31T01:32:45-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_252 (Private Build)"
)
@Component
public class EnderecoMapperImpl implements EnderecoMapper {

    @Override
    public Endereco enderecoDTOtoEndereco(EnderecoDTO enderecoDTO) {
        if ( enderecoDTO == null ) {
            return null;
        }

        Endereco endereco = new Endereco();

        endereco.setId( enderecoDTO.getId() );
        endereco.setLogradouro( enderecoDTO.getLogradouro() );
        endereco.setNumero( enderecoDTO.getNumero() );
        endereco.setBairro( enderecoDTO.getBairro() );
        endereco.setCep( enderecoDTO.getCep() );

        return endereco;
    }

    @Override
    public EnderecoDTO enderecotoEnderecoDTO(Endereco enderecoDTO) {
        if ( enderecoDTO == null ) {
            return null;
        }

        EnderecoDTO enderecoDTO1 = new EnderecoDTO();

        enderecoDTO1.setId( enderecoDTO.getId() );
        enderecoDTO1.setLogradouro( enderecoDTO.getLogradouro() );
        enderecoDTO1.setNumero( enderecoDTO.getNumero() );
        enderecoDTO1.setBairro( enderecoDTO.getBairro() );
        enderecoDTO1.setCep( enderecoDTO.getCep() );

        return enderecoDTO1;
    }

    @Override
    public List<EnderecoDTO> listEnderecoTOListEnderecoDTO(List<Endereco> livros) {
        if ( livros == null ) {
            return null;
        }

        List<EnderecoDTO> list = new ArrayList<EnderecoDTO>( livros.size() );
        for ( Endereco endereco : livros ) {
            list.add( enderecotoEnderecoDTO( endereco ) );
        }

        return list;
    }

    @Override
    public List<Endereco> listEnderecoDTOTOListEndereco(List<EnderecoDTO> livros) {
        if ( livros == null ) {
            return null;
        }

        List<Endereco> list = new ArrayList<Endereco>( livros.size() );
        for ( EnderecoDTO enderecoDTO : livros ) {
            list.add( enderecoDTOtoEndereco( enderecoDTO ) );
        }

        return list;
    }
}
