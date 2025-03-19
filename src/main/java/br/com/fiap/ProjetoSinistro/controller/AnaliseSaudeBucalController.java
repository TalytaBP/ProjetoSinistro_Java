package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.dto.AnaliseSaudeBucalDTO;
import br.com.fiap.ProjetoSinistro.repositorios.AnaliseSaudeBucalRepository;
import br.com.fiap.ProjetoSinistro.repositorios.PacienteRepository;
import br.com.fiap.ProjetoSinistro.service.AnaliseSaudeBucalService;
import br.com.fiap.ProjetoSinistro.service.PacienteService;
import br.com.fiap.ProjetoSinistro.view.AnaliseSaudeBucalView;
import ch.qos.logback.core.model.Model;
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
public class AnaliseSaudeBucalController {

    @Autowired
    AnaliseSaudeBucalRepository analiseSaudeBucalRepository;
    PacienteRepository pacienteRepository;

    @PostMapping("/analise_saude_bucal")
    public ResponseEntity<AnaliseSaudeBucalView> saveAnaliseSaudeBucal(@RequestBody @Valid AnaliseSaudeBucalDTO analiseSaudeBucalDTO) {
        var analiseSaudeBucalView = new AnaliseSaudeBucalView();
        BeanUtils.copyProperties(analiseSaudeBucalDTO, analiseSaudeBucalView);
        return ResponseEntity.status(HttpStatus.CREATED).body(analiseSaudeBucalRepository.save(analiseSaudeBucalView));
    }
    @GetMapping("/analise_saude_bucal")
    public ResponseEntity<List<AnaliseSaudeBucalView>> getAllAnaliseSaudeBucal(){
        List<AnaliseSaudeBucalView> analiseSaudeBucalList = analiseSaudeBucalRepository.findAll();
        if (!analiseSaudeBucalList.isEmpty()) {
            for (AnaliseSaudeBucalView analiseSaudeBucal : analiseSaudeBucalList) {
                UUID id = analiseSaudeBucal.getId_scanner();
                analiseSaudeBucal.add(linkTo(methodOn(AnaliseSaudeBucalController.class).getOneAnaliseSaudeBucal(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(analiseSaudeBucalList);
    }
    @GetMapping("/analise_saude_bucal/{id}")
    public ResponseEntity<Object> getOneAnaliseSaudeBucal(@PathVariable(value = "id") UUID id) {
        Optional<AnaliseSaudeBucalView> analiseSaudeBucalO = analiseSaudeBucalRepository.findById(id);
        if (analiseSaudeBucalO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A análise bucal não foi encontrada.");
        }

        analiseSaudeBucalO.get().add(linkTo(methodOn(AnaliseSaudeBucalController.class).getAllAnaliseSaudeBucal()).withRel("Lista de Telefones"));


        return ResponseEntity.status(HttpStatus.OK).body(analiseSaudeBucalO.get());
    }

    @PutMapping("/analise_saude_bucal/{id}")
    public ResponseEntity<Object> updateAnaliseSaudeBucal(@PathVariable(value="id") UUID id, @RequestBody @Valid AnaliseSaudeBucalDTO analiseSaudeBucalDTO) {

        Optional<AnaliseSaudeBucalView> analiseSaudeBucalO = analiseSaudeBucalRepository.findById(id);
        if (analiseSaudeBucalO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A análise bucal não foi encontrada");
        }
        var AnaliseSaudeBucalView = analiseSaudeBucalO.get();
        BeanUtils.copyProperties(analiseSaudeBucalDTO, AnaliseSaudeBucalView);
        return ResponseEntity.status(HttpStatus.OK).body(analiseSaudeBucalRepository.save(AnaliseSaudeBucalView));
    }
    @DeleteMapping("/analise_saude_bucal/{id}")
    public ResponseEntity<Object> deleteAnaliseSaudeBucal(@PathVariable(value = "id") UUID id) {
        Optional<AnaliseSaudeBucalView> analiseSaudeBucalO = analiseSaudeBucalRepository.findById(id);
        if (analiseSaudeBucalO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A análise bucal não foi encontrada");
        }
        analiseSaudeBucalRepository.delete(analiseSaudeBucalO.get());
        return ResponseEntity.status(HttpStatus.OK).body("A análise bucal foi deletada com sucesso.");
    }
}
