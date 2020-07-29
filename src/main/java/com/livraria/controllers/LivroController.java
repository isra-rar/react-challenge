package com.livraria.controllers;

import com.livraria.dto.AutorDTO;
import com.livraria.dto.LivroDTO;
import com.livraria.entities.Autor;
import com.livraria.entities.Livro;
import com.livraria.mappers.AutorMapper;
import com.livraria.mappers.LivroMapper;
import com.livraria.services.AutorService;
import com.livraria.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class LivroController extends GenericController<LivroService>{

    @Autowired
    private LivroMapper livroMapper;

    @GetMapping(value = "/livros/{id}")
    public ResponseEntity<LivroDTO> findById(@PathVariable Long id) {
        LivroDTO obj = getService().getById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/livros")
    public ResponseEntity<Page<LivroDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPorPage", defaultValue = "10") Integer linesPorPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Livro> list = getService().findPage(page, linesPorPage, orderBy, direction);
        Page<LivroDTO> listDto = list.map(obj -> livroMapper.livroToLivroDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping(value = "/livros")
    public ResponseEntity<LivroDTO> insert(@Valid @RequestBody LivroDTO objDto) {
        LivroDTO livroDTO = getService().insert(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(livroDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(livroDTO);
    }

    @PutMapping(value = "/livros/{id}")
    public ResponseEntity<Void> update(@RequestBody LivroDTO obj, @PathVariable Long id) {
        getService().update(obj, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/livros/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        getService().delete(id);
        return ResponseEntity.noContent().build();
    }

}
