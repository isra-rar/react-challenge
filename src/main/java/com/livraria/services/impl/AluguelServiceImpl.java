package com.livraria.services.impl;

import com.livraria.dto.AluguelDTO;
import com.livraria.dto.ClienteDTO;
import com.livraria.dto.LivroDTO;
import com.livraria.entities.Aluguel;
import com.livraria.mappers.AluguelMapper;
import com.livraria.repositories.AluguelRepository;
import com.livraria.repositories.LivroRepository;
import com.livraria.services.AluguelService;
import com.livraria.services.ClienteService;
import com.livraria.services.LivroService;
import com.livraria.services.excepctions.DataIntegrityException;
import com.livraria.services.excepctions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    public AluguelDTO insert(AluguelDTO objDto) {

        ClienteDTO clienteDTO = clienteService.getById(objDto.getCliente().getId());

        List<LivroDTO> livroDTOS = new ArrayList<>();
        objDto.getLivros().forEach(l -> livroDTOS.add(livroService.getById(l.getId())));
        objDto.setCliente(clienteDTO);
        objDto.setLivros(livroDTOS);

        Aluguel obj = getModelMapper().aluguelDtoToAluguel(objDto);

        obj.setDiaAlugado(LocalDateTime.now());
        obj.setDiaDevolucao(obj.getDiaAlugado().plusDays(7));

        obj.setId(null);
        obj.getLivros().forEach(l -> livroRepository.getOne(l.getId()).setQuantidade((l.getQuantidade() - 1)));
        obj.setValorAlugel(obj.getLivros().stream().mapToDouble((v) -> v.getValor()).sum());
        getRepository().save(obj);

        Aluguel aluguel = getRepository().save(obj);
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
    }
}
