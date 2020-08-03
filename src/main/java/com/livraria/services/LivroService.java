package com.livraria.services;

import com.livraria.dto.LivroDTO;
import com.livraria.entities.Livro;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

public interface LivroService extends GenericService {


    LivroDTO getById(Long id);

    List<LivroDTO> getAll();

    LivroDTO insert(LivroDTO objDto);

    void update(LivroDTO objDto, Long id);

    void delete(Long id);

    Page<Livro> findPage(Integer page, Integer linesPorPage, String orderBy, String direction);
}
