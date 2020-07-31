package com.livraria.mappers;

import com.livraria.dto.AluguelDTO;
import com.livraria.dto.ReservaDTO;
import com.livraria.entities.Aluguel;
import com.livraria.entities.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AluguelMapper {

    AluguelMapper INSTANCE = Mappers.getMapper(AluguelMapper.class);

    AluguelDTO aluguelToAluguelDTO(Aluguel aluguel);

    Aluguel aluguelDtoToAluguel(AluguelDTO aluguelDTO);

    List<AluguelDTO> listAluguelTOListAluguelDTO(List<Aluguel> aluguels);

    Aluguel reservaToAluguel(Reserva reserva);
}
