package com.livraria.mappers;

import com.livraria.dto.ClienteDTO;
import com.livraria.dto.EnderecoDTO;
import com.livraria.entities.Cliente;
import com.livraria.entities.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteDTO clienteToClienteDTO(Cliente cliente);

    Cliente clienteDtoToCliente (ClienteDTO clienteDTO);

    List<ClienteDTO> listClienteTOListClienteDTO(List<Cliente> clientes);


}
