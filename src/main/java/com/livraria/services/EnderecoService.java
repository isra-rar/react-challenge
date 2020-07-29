package com.livraria.services;

import com.livraria.dto.ClienteDTO;
import com.livraria.dto.EnderecoDTO;
import com.livraria.entities.Cliente;
import com.livraria.entities.Endereco;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EnderecoService extends GenericService {


    EnderecoDTO getById(Long id);

    List<EnderecoDTO> getAll();

    EnderecoDTO insert(EnderecoDTO objDto);

    void update(EnderecoDTO objDto, Long id);

    void delete(Long id);

    Page<Endereco> findPage(Integer page, Integer linesPorPage, String orderBy, String direction);

}
