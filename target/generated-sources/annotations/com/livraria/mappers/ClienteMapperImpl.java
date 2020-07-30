package com.livraria.mappers;

import com.livraria.dto.ClienteDTO;
import com.livraria.dto.EnderecoDTO;
import com.livraria.entities.Cliente;
import com.livraria.entities.Endereco;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-30T00:18:22-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_252 (Private Build)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteDTO clienteToClienteDTO(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setId( cliente.getId() );
        clienteDTO.setNome( cliente.getNome() );
        clienteDTO.setSexo( cliente.getSexo() );
        clienteDTO.setCpf( cliente.getCpf() );
        clienteDTO.setEmail( cliente.getEmail() );
        clienteDTO.setEnderecos( enderecoListToEnderecoDTOList( cliente.getEnderecos() ) );

        return clienteDTO;
    }

    @Override
    public Cliente clienteDtoToCliente(ClienteDTO clienteDTO) {
        if ( clienteDTO == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setId( clienteDTO.getId() );
        cliente.setNome( clienteDTO.getNome() );
        cliente.setCpf( clienteDTO.getCpf() );
        cliente.setEmail( clienteDTO.getEmail() );
        cliente.setSexo( clienteDTO.getSexo() );
        cliente.setEnderecos( enderecoDTOListToEnderecoList( clienteDTO.getEnderecos() ) );

        return cliente;
    }

    @Override
    public List<ClienteDTO> listClienteTOListClienteDTO(List<Cliente> clientes) {
        if ( clientes == null ) {
            return null;
        }

        List<ClienteDTO> list = new ArrayList<ClienteDTO>( clientes.size() );
        for ( Cliente cliente : clientes ) {
            list.add( clienteToClienteDTO( cliente ) );
        }

        return list;
    }

    protected EnderecoDTO enderecoToEnderecoDTO(Endereco endereco) {
        if ( endereco == null ) {
            return null;
        }

        EnderecoDTO enderecoDTO = new EnderecoDTO();

        enderecoDTO.setId( endereco.getId() );
        enderecoDTO.setLogradouro( endereco.getLogradouro() );
        enderecoDTO.setNumero( endereco.getNumero() );
        enderecoDTO.setBairro( endereco.getBairro() );
        enderecoDTO.setCep( endereco.getCep() );

        return enderecoDTO;
    }

    protected List<EnderecoDTO> enderecoListToEnderecoDTOList(List<Endereco> list) {
        if ( list == null ) {
            return null;
        }

        List<EnderecoDTO> list1 = new ArrayList<EnderecoDTO>( list.size() );
        for ( Endereco endereco : list ) {
            list1.add( enderecoToEnderecoDTO( endereco ) );
        }

        return list1;
    }

    protected Endereco enderecoDTOToEndereco(EnderecoDTO enderecoDTO) {
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

    protected List<Endereco> enderecoDTOListToEnderecoList(List<EnderecoDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Endereco> list1 = new ArrayList<Endereco>( list.size() );
        for ( EnderecoDTO enderecoDTO : list ) {
            list1.add( enderecoDTOToEndereco( enderecoDTO ) );
        }

        return list1;
    }
}
