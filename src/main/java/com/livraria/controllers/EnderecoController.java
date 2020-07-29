package com.livraria.controllers;

import com.livraria.dto.ClienteDTO;
import com.livraria.dto.EnderecoDTO;
import com.livraria.entities.Cliente;
import com.livraria.entities.Endereco;
import com.livraria.mappers.EnderecoMapper;
import com.livraria.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class EnderecoController extends GenericController<EnderecoService> {

    @Autowired
    private EnderecoMapper enderecoMapper;

    @GetMapping(value = "/enderecos/{id}")
    public ResponseEntity<EnderecoDTO> findById(@PathVariable Long id) {
        EnderecoDTO obj = getService().getById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/enderecos")
    public ResponseEntity<Page<EnderecoDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPorPage", defaultValue = "10") Integer linesPorPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Endereco> list = getService().findPage(page, linesPorPage, orderBy, direction);
        Page<EnderecoDTO> listDto = list.map(obj -> enderecoMapper.enderecotoEnderecoDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping(value = "/enderecos")
    public ResponseEntity<EnderecoDTO> insert(@RequestBody EnderecoDTO objDto) {
        EnderecoDTO enderecoDTO = getService().insert(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(enderecoDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(enderecoDTO);
    }

    @PutMapping(value = "/enderecos/{id}")
    public ResponseEntity<Void> update(@RequestBody EnderecoDTO obj, @PathVariable Long id) {
        getService().update(obj, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/enderecos/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        getService().delete(id);
        return ResponseEntity.noContent().build();
    }

}
