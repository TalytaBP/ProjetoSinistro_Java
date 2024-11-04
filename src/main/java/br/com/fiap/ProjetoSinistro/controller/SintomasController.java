package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.dto.SintomasRecordDto;
import br.com.fiap.ProjetoSinistro.repositorios.SintomasRepositorio;
import br.com.fiap.ProjetoSinistro.view.SintomasView;
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
public class SintomasController {

    @Autowired
    SintomasRepositorio sintomasRepositorio;

    @PostMapping("/sintomas")
    public ResponseEntity<SintomasView> saveSintomas(@RequestBody @Valid SintomasRecordDto sintomasRecordDto) {
        var sintomasView = new SintomasView();
        BeanUtils.copyProperties(sintomasRecordDto, sintomasView);
        return (ResponseEntity<SintomasView>) ResponseEntity.status(HttpStatus.CREATED);
    }
    @GetMapping("/sintomas")
    public ResponseEntity<List<SintomasRepositorio>> getAllSintomas(){
        List<SintomasRepositorio> sintomasList = sintomasRepositorio.findAll();
        if (!sintomasList.isEmpty()) {
            for (SintomasRepositorio sintomas : sintomasList) {
                UUID id = sintomas.getId_sintoma();
                sintomas.add(linkTo(methodOn(SintomasController.class).getOneSintomas(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(sintomasList);
    }
    @GetMapping("/sintomas/{id}")
    public ResponseEntity<Object> getOneSintomas(@PathVariable(value = "id") UUID id) {
        Optional<SintomasRepositorio> sintomasO = sintomasRepositorio.findById(id);
        if (sintomasO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Os sintomas não foi encontrado.");
        }

        sintomasO.get().add(linkTo(methodOn(SintomasController.class).getAllSintomas()).withRel("Lista de Sintomas"));


        return ResponseEntity.status(HttpStatus.OK).body(sintomasO.get());
    }
    @PutMapping("/sintomas/{id}")
    public ResponseEntity<Object> updateSintomas(@PathVariable(value="id") UUID id, @RequestBody @Valid SintomasRecordDto sintomasRecordDto) {

        Optional<SintomasRepositorio> sintomasO = sintomasRepositorio.findById(id);
        if (sintomasO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Sintomas não foi encontrado");
        }
        var SintomasView = sintomasO.get();
        BeanUtils.copyProperties(sintomasRecordDto, SintomasView);
        return ResponseEntity.status(HttpStatus.OK).body(sintomasRepositorio.save(SintomasView));
    }
    @DeleteMapping("/sintomas/{id}")
    public ResponseEntity<Object> deleteSintomas(@PathVariable(value = "id") UUID id) {
        Optional<SintomasRepositorio> sintomasO = sintomasRepositorio.findById(id);
        if (sintomasO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Os Sintomas não foram encontrados");
        }
        sintomasRepositorio.delete((SintomasRepositorio) sintomasO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Sintomas foi deletado com sucesso.");
    }

}
