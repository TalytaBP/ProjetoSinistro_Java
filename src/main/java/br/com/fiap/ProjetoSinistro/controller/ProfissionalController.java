package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.dto.ProfissionalDTO;
import br.com.fiap.ProjetoSinistro.repositorios.ProfissionalRepository;
import br.com.fiap.ProjetoSinistro.view.ProfissionalView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
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
@RequestMapping
@AllArgsConstructor
@Log
public class ProfissionalController {

    @Autowired
    ProfissionalRepository profissionalRepository;

    @PostMapping("/profissional")
    public ResponseEntity<ProfissionalView> saveProfissional(@RequestBody @Valid ProfissionalDTO profissionalDTO) {
        var profissionalView = new ProfissionalView();
        BeanUtils.copyProperties(profissionalDTO, profissionalView);
        return ResponseEntity.status(HttpStatus.CREATED).body(profissionalRepository.save(profissionalView));
    }
    @GetMapping("/profissional")
    public ResponseEntity<List<ProfissionalView>> getAllProfissional(){
        List<ProfissionalView> profissionalList = profissionalRepository.findAll();
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
        Optional<ProfissionalView> profissionalO = profissionalRepository.findById(id);
        if (profissionalO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O profissional não foi encontrado.");
        }

        profissionalO.get().add(linkTo(methodOn(ProfissionalController.class).getAllProfissional()).withRel("Lista de profissionais"));


        return ResponseEntity.status(HttpStatus.OK).body(profissionalO.get());
    }
    @PutMapping("/profissional/{id}")
    public ResponseEntity<Object> updateProfissional(@PathVariable(value="id") UUID id, @RequestBody @Valid ProfissionalDTO profissionalDTO) {

        Optional<ProfissionalView> profissionalO = profissionalRepository.findById(id);
        if (profissionalO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O profissional não foi encontrado");
        }
        var ProfissionalView = profissionalO.get();
        BeanUtils.copyProperties(profissionalDTO, ProfissionalView);
        return ResponseEntity.status(HttpStatus.OK).body(profissionalRepository.save(ProfissionalView));
    }
    @DeleteMapping("/profissional/{id}")
    public ResponseEntity<Object> deleteProfissional(@PathVariable(value = "id") UUID id) {
        Optional<ProfissionalView> profissionalO = profissionalRepository.findById(id);
        if (profissionalO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O profissional não foi encontrado");
        }
        profissionalRepository.delete(profissionalO.get());
        return ResponseEntity.status(HttpStatus.OK).body("O profissional foi deletado com sucesso.");
    }
}
