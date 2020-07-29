package com.livraria.services;

import com.livraria.dto.ClienteDTO;
import com.livraria.entities.Cliente;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClienteService extends GenericService {


    ClienteDTO getById(Long id);

    List<ClienteDTO> getAll();

    ClienteDTO insert(ClienteDTO objDto);

    void update(ClienteDTO objDto, Long id);

    void delete(Long id);

    Page<Cliente> findPage(Integer page, Integer linesPorPage, String orderBy, String direction);

}
