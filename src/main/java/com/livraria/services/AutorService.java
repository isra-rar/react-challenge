package com.livraria.services;

import com.livraria.dto.AutorDTO;
import com.livraria.dto.ClienteDTO;
import com.livraria.dto.LivroDTO;
import com.livraria.entities.Autor;
import com.livraria.entities.Cliente;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AutorService extends GenericService {


    AutorDTO getById(Long id);

    List<AutorDTO> getAll();

    AutorDTO insert(AutorDTO objDto);

    void update(AutorDTO objDto, Long id);

    void delete(Long id);

    Page<Autor> findPage(Integer page, Integer linesPorPage, String orderBy, String direction);

    List<LivroDTO> getLivrosByAutor(Long id);
}
