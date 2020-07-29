package com.livraria.mappers;

import com.livraria.dto.AluguelDTO;
import com.livraria.dto.AutorDTO;
import com.livraria.dto.ClienteDTO;
import com.livraria.dto.EnderecoDTO;
import com.livraria.dto.LivroDTO;
import com.livraria.entities.Aluguel;
import com.livraria.entities.Autor;
import com.livraria.entities.Cliente;
import com.livraria.entities.Endereco;
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
public class AluguelMapperImpl implements AluguelMapper {

    @Override
    public AluguelDTO aluguelToAluguelDTO(Aluguel aluguel) {
        if ( aluguel == null ) {
            return null;
        }

        AluguelDTO aluguelDTO = new AluguelDTO();

        aluguelDTO.setId( aluguel.getId() );
        aluguelDTO.setCliente( clienteToClienteDTO( aluguel.getCliente() ) );
        aluguelDTO.setLivros( livroListToLivroDTOList( aluguel.getLivros() ) );
        aluguelDTO.setValorAluguel( aluguel.getValorAluguel() );

        return aluguelDTO;
    }

    @Override
    public Aluguel aluguelDtoToAluguel(AluguelDTO aluguelDTO) {
        if ( aluguelDTO == null ) {
            return null;
        }

        Aluguel aluguel = new Aluguel();

        aluguel.setId( aluguelDTO.getId() );
        aluguel.setCliente( clienteDTOToCliente( aluguelDTO.getCliente() ) );
        aluguel.setLivros( livroDTOListToLivroList( aluguelDTO.getLivros() ) );
        aluguel.setValorAluguel( aluguelDTO.getValorAluguel() );

        return aluguel;
    }

    @Override
    public List<AluguelDTO> listAluguelTOListAluguelDTO(List<Aluguel> aluguels) {
        if ( aluguels == null ) {
            return null;
        }

        List<AluguelDTO> list = new ArrayList<AluguelDTO>( aluguels.size() );
        for ( Aluguel aluguel : aluguels ) {
            list.add( aluguelToAluguelDTO( aluguel ) );
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

    protected ClienteDTO clienteToClienteDTO(Cliente cliente) {
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

    protected AutorDTO autorToAutorDTO(Autor autor) {
        if ( autor == null ) {
            return null;
        }

        AutorDTO autorDTO = new AutorDTO();

        autorDTO.setId( autor.getId() );
        autorDTO.setNome( autor.getNome() );

        return autorDTO;
    }

    protected LivroDTO livroToLivroDTO(Livro livro) {
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

    protected List<LivroDTO> livroListToLivroDTOList(List<Livro> list) {
        if ( list == null ) {
            return null;
        }

        List<LivroDTO> list1 = new ArrayList<LivroDTO>( list.size() );
        for ( Livro livro : list ) {
            list1.add( livroToLivroDTO( livro ) );
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

    protected Cliente clienteDTOToCliente(ClienteDTO clienteDTO) {
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

    protected Autor autorDTOToAutor(AutorDTO autorDTO) {
        if ( autorDTO == null ) {
            return null;
        }

        Autor autor = new Autor();

        autor.setId( autorDTO.getId() );
        autor.setNome( autorDTO.getNome() );

        return autor;
    }

    protected Livro livroDTOToLivro(LivroDTO livroDTO) {
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

    protected List<Livro> livroDTOListToLivroList(List<LivroDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Livro> list1 = new ArrayList<Livro>( list.size() );
        for ( LivroDTO livroDTO : list ) {
            list1.add( livroDTOToLivro( livroDTO ) );
        }

        return list1;
    }
}
