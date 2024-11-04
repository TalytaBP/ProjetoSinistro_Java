package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.dto.PlanoRecordDto;
import br.com.fiap.ProjetoSinistro.repositorios.PlanoRepositorio;
import br.com.fiap.ProjetoSinistro.view.PlanoView;
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
public class PlanoController {

    @Autowired
    PlanoRepositorio planoRepositorio;

    @PostMapping("/plano")
    public ResponseEntity<PlanoView> savePlano(@RequestBody @Valid PlanoRecordDto planoRecordDto) {
        var planoView = new PlanoView();
        BeanUtils.copyProperties(planoRecordDto, planoView);
        return ResponseEntity.status(HttpStatus.CREATED).body(planoRepositorio.save(planoView));
    }
    @GetMapping("/plano")
    public ResponseEntity<List<PlanoView>> getAllPlano(){
        List<PlanoView> planoList = planoRepositorio.findAll();
        if (!planoList.isEmpty()) {
            for (PlanoView plano : planoList) {
                UUID id = plano.getId_plano();
                plano.add(linkTo(methodOn(PlanoController.class).getOnePlano(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(planoList);
    }
    @GetMapping("/plano/{id}")
    public ResponseEntity<Object> getOnePlano(@PathVariable(value = "id") UUID id) {
        Optional<PlanoView> planoO = planoRepositorio.findById(id);
        if (planoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O plano não foi encontrado");
        }

        planoO.get().add(linkTo(methodOn(PlanoController.class).getAllPlano()).withRel("Lista de planos"));


        return ResponseEntity.status(HttpStatus.OK).body(planoO.get());
    }
    @PutMapping("/plano/{id}")
    public ResponseEntity<Object> updatePlano(@PathVariable(value="id") UUID id, @RequestBody @Valid PlanoRecordDto planoRecordDto) {

        Optional<PlanoView> planoO = planoRepositorio.findById(id);
        if (planoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O plano não foi encontrado");
        }
        var TelefoneView = planoO.get();
        Object PlanoView = null;
        BeanUtils.copyProperties(planoRecordDto, PlanoView);
        return ResponseEntity.status(HttpStatus.OK).body(planoRepositorio.save(TelefoneView));
    }
    @DeleteMapping("/plano/{id}")
    public ResponseEntity<Object> deletePlano(@PathVariable(value = "id") UUID id) {
        Optional<PlanoView> planoO = planoRepositorio.findById(id);
        if (planoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O plano não foi encontrado");
        }
        planoRepositorio.delete(planoO.get());
        return ResponseEntity.status(HttpStatus.OK).body("O plano foi deletado com sucesso.");
    }
}
