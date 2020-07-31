package com.livraria.mappers;

import com.livraria.dto.ReservaDTO;
import com.livraria.entities.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    ReservaMapper INSTANCE = Mappers.getMapper(ReservaMapper.class);

    ReservaDTO reservaToReservaDTO(Reserva reserva);

    Reserva reservaDtoToReserva(ReservaDTO reservaDTO);

    List<ReservaDTO> listReservaTOListReservaDTO(List<Reserva> reservas);

}
