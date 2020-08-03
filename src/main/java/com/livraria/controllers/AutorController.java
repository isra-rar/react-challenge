package com.livraria.controllers;

import com.livraria.dto.AutorDTO;
import com.livraria.dto.LivroDTO;
import com.livraria.entities.Autor;
import com.livraria.mappers.AutorMapper;
import com.livraria.services.AutorService;
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
public class AutorController extends GenericController<AutorService>{

    @Autowired
    private AutorMapper autorMapper;

    @GetMapping(value = "/autores/{id}")
    public ResponseEntity<AutorDTO> findById(@PathVariable Long id) {
        AutorDTO obj = getService().getById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/autores")
    public ResponseEntity<Page<AutorDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPorPage", defaultValue = "10") Integer linesPorPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Autor> list = getService().findPage(page, linesPorPage, orderBy, direction);
        Page<AutorDTO> listDto = list.map(obj -> autorMapper.autorToAutorDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping(value = "/autores")
    public ResponseEntity<AutorDTO> insert(@Valid @RequestBody AutorDTO objDto) {
        AutorDTO autorDTO = getService().insert(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(autorDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(autorDTO);
    }
    @CrossOrigin(origins = "*")
    @PutMapping(value = "/autores/{id}")
    public ResponseEntity<Void> update(@RequestBody AutorDTO obj, @PathVariable Long id) {
        getService().update(obj, id);
        return ResponseEntity.noContent().build();
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/autores/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        getService().delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/autores/{id}/livros")
    public ResponseEntity<List<LivroDTO>> getLivrosAutor(@PathVariable Long id) {
        List<LivroDTO> livrosDTO = getService().getLivrosByAutor(id);
        return ResponseEntity.ok().body(livrosDTO);
    }

}
