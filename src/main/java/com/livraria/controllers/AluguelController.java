package com.livraria.controllers;

import com.livraria.dto.AluguelDTO;
import com.livraria.entities.Aluguel;
import com.livraria.mappers.AluguelMapper;
import com.livraria.services.AluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api")
public class AluguelController extends GenericController<AluguelService> {

    @Autowired
    private AluguelMapper aluguelMapper;

    @GetMapping(value = "/alugueis/{id}")
    public ResponseEntity<AluguelDTO> findById(@PathVariable Long id) {
        AluguelDTO obj = getService().getById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/alugueis")
    public ResponseEntity<Page<AluguelDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPorPage", defaultValue = "10") Integer linesPorPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Aluguel> list = getService().findPage(page, linesPorPage, orderBy, direction);
        Page<AluguelDTO> listDto = list.map(obj -> aluguelMapper.aluguelToAluguelDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping(value = "/alugueis")
    public ResponseEntity<AluguelDTO> insert(@Valid @RequestBody AluguelDTO objDto) {
        AluguelDTO aluguelDTO = getService().insert(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(aluguelDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(aluguelDTO);
    }

    @GetMapping(value = "alugueis/devolucao/{id}")
    public ResponseEntity<Void> devolucaoLivros(@PathVariable Long id){
        getService().devolucaoAluguel(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping(value = "/alugueis/{id}")
    public ResponseEntity<Void> update(@RequestBody AluguelDTO obj, @PathVariable Long id) {
        getService().update(obj, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/alugueis/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        getService().delete(id);
        return ResponseEntity.noContent().build();
    }

}
