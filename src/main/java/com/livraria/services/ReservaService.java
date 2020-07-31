package com.livraria.services;

import com.livraria.dto.ReservaDTO;
import com.livraria.entities.Reserva;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReservaService extends GenericService{

    ReservaDTO getById(Long id);

    List<ReservaDTO> getAll();

    ReservaDTO insert(ReservaDTO objDto);

    void retirarReserva(Long id);

    void cancelarReserva(Long id);

    void update(ReservaDTO objDto, Long id);

    void delete(Long id);

    Page<Reserva> findPage(Integer page, Integer linesPorPage, String orderBy, String direction);

}
