package com.livraria.controllers;

import com.livraria.dto.ClienteDTO;
import com.livraria.entities.Cliente;
import com.livraria.mappers.ClienteMapper;
import com.livraria.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api")
public class ClienteController extends GenericController<ClienteService> {

    @Autowired
    private ClienteMapper clienteMapper;

    @GetMapping(value = "/clientes/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
        ClienteDTO obj = getService().getById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/clientes")
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPorPage", defaultValue = "10") Integer linesPorPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Cliente> list = getService().findPage(page, linesPorPage, orderBy, direction);
        Page<ClienteDTO> listDto = list.map(obj -> clienteMapper.clienteToClienteDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping(value = "/clientes")
    public ResponseEntity<ClienteDTO> insert(@Valid @RequestBody ClienteDTO objDto) {
        ClienteDTO clienteDTO = getService().insert(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(clienteDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(clienteDTO);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(value = "/clientes/{id}")
    public ResponseEntity<Void> update(@RequestBody ClienteDTO obj, @PathVariable Long id) {
        getService().update(obj, id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/clientes/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        getService().delete(id);
        return ResponseEntity.noContent().build();
    }

}
