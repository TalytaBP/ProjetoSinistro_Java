package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.dto.ProfissionalRecordDto;
import br.com.fiap.ProjetoSinistro.repositorios.ProfissionalRepositorio;
import br.com.fiap.ProjetoSinistro.view.ProfissionalView;
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
public class ProfissionalController {

    @Autowired
    ProfissionalRepositorio profissionalRepositorio;

    @PostMapping("/profissional")
    public ResponseEntity<ProfissionalView> saveProfissional(@RequestBody @Valid ProfissionalRecordDto profissionalRecordDto) {
        var profissionalView = new ProfissionalView();
        BeanUtils.copyProperties(profissionalRecordDto, profissionalView);
        return ResponseEntity.status(HttpStatus.CREATED).body(profissionalRepositorio.save(profissionalView));
    }
    @GetMapping("/profissional")
    public ResponseEntity<List<ProfissionalView>> getAllProfissional(){
        List<ProfissionalView> profissionalList = profissionalRepositorio.findAll();
        if (!profissionalList.isEmpty()) {
            for (ProfissionalView profissional : profissionalList) {
                UUID id = profissional.getId_profissional();
                profissional.add(linkTo(methodOn(ProfissionalController.class).getOneProfissional(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(profissionalList);
    }
    @GetMapping("/profissional/{id}")
    public ResponseEntity<Object> getOneProfissional(@PathVariable(value = "id") UUID id) {
        Optional<ProfissionalView> profissionalO = profissionalRepositorio.findById(id);
        if (profissionalO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O profissional não foi encontrado.");
        }

        profissionalO.get().add(linkTo(methodOn(ProfissionalController.class).getAllProfissional()).withRel("Lista de profissionais"));


        return ResponseEntity.status(HttpStatus.OK).body(profissionalO.get());
    }
    @PutMapping("/profissional/{id}")
    public ResponseEntity<Object> updateProfissional(@PathVariable(value="id") UUID id, @RequestBody @Valid ProfissionalRecordDto profissionalRecordDto) {

        Optional<ProfissionalView> profissionalO = profissionalRepositorio.findById(id);
        if (profissionalO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O profissional não foi encontrado");
        }
        var ProfissionalView = profissionalO.get();
        BeanUtils.copyProperties(profissionalRecordDto, ProfissionalView);
        return ResponseEntity.status(HttpStatus.OK).body(profissionalRepositorio.save(ProfissionalView));
    }
    @DeleteMapping("/profissional/{id}")
    public ResponseEntity<Object> deleteProfissional(@PathVariable(value = "id") UUID id) {
        Optional<ProfissionalView> profissionalO = profissionalRepositorio.findById(id);
        if (profissionalO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O profissional não foi encontrado");
        }
        profissionalRepositorio.delete(profissionalO.get());
        return ResponseEntity.status(HttpStatus.OK).body("O profissional foi deletado com sucesso.");
    }
}
