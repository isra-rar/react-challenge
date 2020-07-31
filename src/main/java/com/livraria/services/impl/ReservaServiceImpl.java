package com.livraria.services.impl;

import com.livraria.dto.AluguelDTO;
import com.livraria.dto.ClienteDTO;
import com.livraria.dto.LivroDTO;
import com.livraria.dto.ReservaDTO;
import com.livraria.entities.Aluguel;
import com.livraria.entities.Livro;
import com.livraria.entities.Reserva;
import com.livraria.mappers.AluguelMapper;
import com.livraria.mappers.ReservaMapper;
import com.livraria.repositories.AluguelRepository;
import com.livraria.repositories.LivroRepository;
import com.livraria.repositories.ReservaRepository;
import com.livraria.services.AluguelService;
import com.livraria.services.ClienteService;
import com.livraria.services.LivroService;
import com.livraria.services.ReservaService;
import com.livraria.services.excepctions.DataIntegrityException;
import com.livraria.services.excepctions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl extends GenericServiceImpl<ReservaRepository, ReservaMapper> implements ReservaService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private LivroService livroService;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AluguelService aluguelService;

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private AluguelMapper aluguelMapper;

    @Override
    public ReservaDTO getById(Long id) {
        Optional<Reserva> obj = getRepository().findById(id);
        obj.orElseThrow(() -> new ObjectNotFoundException(
                "Reserva não encontrado"));
        return getModelMapper().reservaToReservaDTO(obj.get());
    }

    @Override
    public List<ReservaDTO> getAll() {
        List<Reserva> obj = getRepository().findAll();
        return getModelMapper().listReservaTOListReservaDTO(obj);
    }

    @Override
    public ReservaDTO insert(ReservaDTO objDto) {

        ClienteDTO clienteDTO = clienteService.getById(objDto.getCliente().getId());

        List<LivroDTO> livroDTOS = new ArrayList<>();
        objDto.getLivros().forEach(l -> livroDTOS.add(livroService.getById(l.getId())));
        objDto.setCliente(clienteDTO);
        objDto.setLivros(livroDTOS);

        Reserva obj = getModelMapper().reservaDtoToReserva(objDto);

        obj.setDiaReserva(LocalDateTime.now());
        obj.setRetirado(false);
        obj.setCancelado(false);
        obj.setId(null);
        obj.setIdAluguel(null);

        for (Livro livro : obj.getLivros()) {
            if (livro.getUnidsReserva() == null) {
                livro.setUnidsReserva(0);
                livro.setUnidsReserva(livro.getUnidsReserva() + 1);
            } else {
                livro.setUnidsReserva(livro.getUnidsReserva() + 1);
            }
            livroRepository.save(livro);
        }

        obj.setValorTotal(obj.getLivros().stream().mapToDouble((v) -> v.getValor()).sum());
        getRepository().save(obj);

        Reserva reserva = getRepository().save(obj);
        return getModelMapper().reservaToReservaDTO(reserva);
    }

    @Override
    public void retirarReserva(Long id) {
        ReservaDTO reservaDTO = getById(id);
        Reserva reserva = getModelMapper().reservaDtoToReserva(reservaDTO);

        reserva.setDiaRetirada(LocalDateTime.now());
        reserva.setRetirado(true);

        AluguelDTO aluguelDTO = aluguelService.transformarReservaEmAluguel(id);

        Aluguel aluguel = aluguelMapper.aluguelDtoToAluguel(aluguelDTO);
        aluguelRepository.save(aluguel);

        reserva.setIdAluguel(aluguel.getId());

        getRepository().save(reserva);
    }

    @Override
    public void cancelarReserva(Long id) {
        ReservaDTO reservaDTO = getById(id);
        reservaDTO.getLivros().forEach(l -> livroService.getById(l.getId()).setUnidsReserva((l.getUnidsReserva() - 1)));
        reservaDTO.setCancelado(true);

        getRepository().save(getModelMapper().reservaDtoToReserva(reservaDTO));
    }

    @Override
    public void update(ReservaDTO objDto, Long id) {
        ReservaDTO reservaDTO = getById(id);
        updateData(reservaDTO, objDto);
        Reserva reserva = getModelMapper().reservaDtoToReserva(reservaDTO);
        getRepository().save(reserva);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        try {
            getRepository().deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir um RESERVA que possua associações");
        }
    }

    @Override
    public Page<Reserva> findPage(Integer page, Integer linesPorPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPorPage, Sort.Direction.valueOf(direction), orderBy);
        return getRepository().findByReserva(pageRequest);
    }

    private void updateData(ReservaDTO newObj, ReservaDTO obj) {
        newObj.setCliente(obj.getCliente());
        newObj.setLivros(obj.getLivros());
        newObj.setDiaRetirada(obj.getDiaRetirada());
        newObj.setRetirado(obj.getRetirado());
        newObj.setCancelado(obj.getCancelado());
        newObj.setValorTotal(obj.getValorTotal());
    }
}
