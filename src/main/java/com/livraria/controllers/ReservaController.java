package com.livraria.controllers;

import com.livraria.dto.AluguelDTO;
import com.livraria.dto.ReservaDTO;
import com.livraria.entities.Reserva;
import com.livraria.mappers.ReservaMapper;
import com.livraria.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api")
public class ReservaController extends GenericController<ReservaService> {

    @Autowired
    private ReservaMapper reservaMapper;

    @GetMapping(value = "/reservas/{id}")
    public ResponseEntity<ReservaDTO> findById(@PathVariable Long id) {
        ReservaDTO obj = getService().getById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/reservas")
    public ResponseEntity<Page<ReservaDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPorPage", defaultValue = "10") Integer linesPorPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Reserva> list = getService().findPage(page, linesPorPage, orderBy, direction);
        Page<ReservaDTO> listDto = list.map(obj -> reservaMapper.reservaToReservaDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping(value = "/reservas")
    public ResponseEntity<ReservaDTO> insert(@Valid @RequestBody ReservaDTO objDto) {
        ReservaDTO reservaDTO = getService().insert(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(reservaDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(reservaDTO);
    }

    @GetMapping(value = "/reservas/retirar/{id}")
    public ResponseEntity<Void> retirarReserva(@PathVariable Long id){
        getService().retirarReserva(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/reservas/cancelar/{id}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long id){
        getService().cancelarReserva(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/reservas/{id}")
    public ResponseEntity<Void> update(@RequestBody ReservaDTO obj, @PathVariable Long id) {
        getService().update(obj, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/reservas/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        getService().delete(id);
        return ResponseEntity.noContent().build();
    }
}
