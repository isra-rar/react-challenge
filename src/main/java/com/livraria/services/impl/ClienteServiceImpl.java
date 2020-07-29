package com.livraria.services.impl;

import com.livraria.dto.ClienteDTO;
import com.livraria.entities.Cliente;
import com.livraria.entities.Endereco;
import com.livraria.mappers.ClienteMapper;
import com.livraria.repositories.ClienteRepository;
import com.livraria.services.ClienteService;
import com.livraria.services.excepctions.DataIntegrityException;
import com.livraria.services.excepctions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl extends GenericServiceImpl<ClienteRepository, ClienteMapper> implements ClienteService {


    @Override
    public ClienteDTO getById(Long id) {
        Optional<Cliente> obj = getRepository().findById(id);
        obj.orElseThrow(() -> new ObjectNotFoundException(
                "Cliente não encontrado"));
        return getModelMapper().clienteToClienteDTO(obj.get());
    }

    @Override
    public List<ClienteDTO> getAll() {
        List<Cliente> obj = getRepository().findAll();
        return getModelMapper().listClienteTOListClienteDTO(obj);
    }

    @Override
    public ClienteDTO insert(ClienteDTO objDto) {
        Cliente obj = getModelMapper().clienteDtoToCliente(objDto);
        obj.setId(null);
        obj.getEnderecos().forEach(e -> e.setCliente(obj));
        Cliente cliente = getRepository().save(obj);
        return getModelMapper().clienteToClienteDTO(cliente);
    }

    @Override
    public void update(ClienteDTO objDto, Long id) {
        ClienteDTO clienteDTO = getById(id);
        updateData(clienteDTO, objDto);
        Cliente cliente = getModelMapper().clienteDtoToCliente(clienteDTO);
        cliente.getEnderecos().forEach(e -> e.setCliente(cliente));
        getRepository().save(cliente);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        try {
            getRepository().deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir um CLIENTE que possua associações");
        }
    }

    @Override
    public Page<Cliente> findPage(Integer page, Integer linesPorPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPorPage, Sort.Direction.valueOf(direction), orderBy);
        return getRepository().findByCliente(pageRequest);
    }

    private void updateData(ClienteDTO newObj, ClienteDTO obj) {
        newObj.setNome(obj.getNome());
        newObj.setSexo(obj.getSexo());
        newObj.setCpf(obj.getCpf());
        newObj.setEmail(obj.getEmail());
        newObj.setEnderecos(obj.getEnderecos());
    }
}
