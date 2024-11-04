package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.dto.EstadoRecordDto;
import br.com.fiap.ProjetoSinistro.repositorios.EstadoRepositorio;
import br.com.fiap.ProjetoSinistro.view.EstadoView;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EstadoController {

    @Autowired
    EstadoRepositorio estadoRepositorio;

    @PostMapping("/estado")
    public ResponseEntity<EstadoView> saveEstado(@RequestBody @Valid EstadoRecordDto estadoRecordDto) {
        var estadoView = new EstadoView();
        BeanUtils.copyProperties(estadoRecordDto, estadoView);
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoRepositorio.save(estadoView));
    }
    @GetMapping("/estado")
    public ResponseEntity<List<EstadoView>> getAllEstado(){
        List<EstadoView> estadoList = estadoRepositorio.findAll();
        if (!estadoList.isEmpty()) {
            for (EstadoView estado : estadoList) {
                UUID id = estado.getId_estado();
                estado.add(linkTo(methodOn(EstadoController.class).getOneEstado(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(estadoList);
    }
    @GetMapping("/estado/{id}")
    public ResponseEntity<Object> getOneEstado(@PathVariable(value = "id") UUID id) {
        Optional<EstadoView> estadoO = estadoRepositorio.findById(id);
        if (estadoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O estado não foi encontrado.");
        }

        estadoO.get().add(linkTo(methodOn(EstadoController.class).getAllEstado()).withRel("Lista de estados"));


        return ResponseEntity.status(HttpStatus.OK).body(estadoO.get());
    }
    @PutMapping("/estado/{id}")
    public ResponseEntity<Object> updateEstado(@PathVariable(value="id") UUID id, @RequestBody @Valid EstadoRecordDto estadoRecordDto) {

        Optional<EstadoView> estadoO = estadoRepositorio.findById(id);
        if (estadoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O estado não foi encontrado");
        }
        var EstadoView = estadoO.get();
        BeanUtils.copyProperties(estadoRecordDto, EstadoView);
        return ResponseEntity.status(HttpStatus.OK).body(estadoRepositorio.save(EstadoView));
    }
    @DeleteMapping("/estado/{id}")
    public ResponseEntity<Object> deleteEstado(@PathVariable(value = "id") UUID id) {
        Optional<EstadoView> estadoO = estadoRepositorio.findById(id);
        if (estadoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O estado não foi encontrado");
        }
        estadoRepositorio.delete(estadoO.get());
        return ResponseEntity.status(HttpStatus.OK).body("O estado foi deletado com sucesso.");
    }
}
