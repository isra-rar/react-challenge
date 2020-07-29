package com.livraria.services;

import com.livraria.dto.AluguelDTO;
import com.livraria.entities.Aluguel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AluguelService extends GenericService {

    AluguelDTO getById(Long id);

    List<AluguelDTO> getAll();

    AluguelDTO insert(AluguelDTO objDto);

    void update(AluguelDTO objDto, Long id);

    void delete(Long id);

    Page<Aluguel> findPage(Integer page, Integer linesPorPage, String orderBy, String direction);
}
