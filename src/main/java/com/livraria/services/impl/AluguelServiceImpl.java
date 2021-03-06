package com.livraria.services.impl;

import com.livraria.dto.*;
import com.livraria.entities.Aluguel;
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

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AluguelServiceImpl extends GenericServiceImpl<AluguelRepository, AluguelMapper> implements AluguelService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private LivroService livroService;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ReservaMapper reservaMapper;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat formato = new DecimalFormat("#.##");

    @Override
    public AluguelDTO getById(Long id) {
        Optional<Aluguel> obj = getRepository().findById(id);
        obj.orElseThrow(() -> new ObjectNotFoundException(
                "Aluguel não encontrado"));
        return getModelMapper().aluguelToAluguelDTO(obj.get());
    }

    @Override
    public List<AluguelDTO> getAll() {
        List<Aluguel> obj = getRepository().findAll();
        return getModelMapper().listAluguelTOListAluguelDTO(obj);
    }

    @Override
    public AluguelDTO insertAluguel(AluguelDTO objDto) {

        Calendar calendar = Calendar.getInstance();


        ClienteDTO clienteDTO = clienteService.getById(objDto.getCliente().getId());

        List<LivroDTO> livroDTOS = new ArrayList<>();
        objDto.getLivros().forEach(l -> livroDTOS.add(livroService.getById(l.getId())));
        objDto.setCliente(clienteDTO);
        objDto.setLivros(livroDTOS);

        Aluguel obj = getModelMapper().aluguelDtoToAluguel(objDto);

        obj.setDiaAlugado(dateFormat.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH, 2);
        obj.setDiaDevolucao(dateFormat.format(calendar.getTime()));

        obj.setId(null);
        obj.getLivros().stream().forEach(l -> {

            if (l.getUnidsReserva() == null) {
                l.setUnidsReserva(0);
            }
            if (l.getQuantidade() - l.getUnidsReserva() <= 0) {
                throw new DataIntegrityException("O livro: " + l.getTitulo() + " está indisponivel. Quantidade para reserva menor que 0");
            }
            if (l.getQuantidade() > 0) {
                l.setQuantidade(l.getQuantidade() - 1);
                livroRepository.save(l);
            } else {
                throw new DataIntegrityException("O livro: " + l.getTitulo() + " está indisponivel. Quantidade menor que 0");
            }
        });
        obj.setValorAlugel(obj.getLivros().stream().mapToDouble((v) -> v.getValor()).sum());
        getRepository().save(obj);

        Aluguel aluguel = getRepository().save(obj);
        return getModelMapper().aluguelToAluguelDTO(aluguel);
    }

    @Override
    public AluguelDTO transformarReservaEmAluguel(Long id) {
        Calendar calendar = Calendar.getInstance();

        ReservaDTO reservaDTO = reservaService.getById(id);
        Reserva reserva = reservaMapper.reservaDtoToReserva(reservaDTO);
        reserva.getLivros().stream().forEach(l -> {
            l.setUnidsReserva(l.getUnidsReserva() - 1);
            livroRepository.save(l);
        });

        Aluguel aluguel = getModelMapper().reservaToAluguel(reserva);

        aluguel.setDiaAlugado(dateFormat.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH, 10);
        aluguel.setDiaDevolucao(dateFormat.format(calendar.getTime()));

        aluguel.setId(null);

        aluguel.getLivros().stream().forEach(l -> {
            if (l.getQuantidade() > 0) {
                l.setQuantidade(l.getQuantidade() - 1);
                livroRepository.save(l);
            } else {
                throw new DataIntegrityException("O livro: " + l.getTitulo() + " está indisponivel. Quantidade menor que 0");
            }
        });
        aluguel.setValorAlugel(aluguel.getLivros().stream().mapToDouble((v) -> v.getValor()).sum());

        getRepository().save(aluguel);
        return getModelMapper().aluguelToAluguelDTO(aluguel);
    }

    @Override
    public void devolucaoAluguel(Long id) {
        AluguelDTO aluguelDTO = getById(id);
        aluguelDTO.getLivros().forEach(l -> livroRepository.getOne(l.getId()).setQuantidade((l.getQuantidade() + 1)));
        getRepository().save(getModelMapper().aluguelDtoToAluguel(aluguelDTO));
    }

    @Override
    public void update(AluguelDTO objDto, Long id) {
        AluguelDTO aluguelDTO = getById(id);
        updateData(aluguelDTO, objDto);
        Aluguel aluguel = getModelMapper().aluguelDtoToAluguel(aluguelDTO);
        getRepository().save(aluguel);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        try {
            getRepository().deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir um ALUGUEL que possua associações");
        }
    }

    @Override
    public Page<Aluguel> findPage(Integer page, Integer linesPorPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPorPage, Sort.Direction.valueOf(direction), orderBy);
        return getRepository().findByAluguel(pageRequest);
    }

    private void updateData(AluguelDTO newObj, AluguelDTO obj) {
        newObj.setCliente(obj.getCliente());
        newObj.setLivros(obj.getLivros());
        newObj.setDiaDevolucao(obj.getDiaDevolucao());
        newObj.setValorAluguel(obj.getValorAluguel());
    }

}
