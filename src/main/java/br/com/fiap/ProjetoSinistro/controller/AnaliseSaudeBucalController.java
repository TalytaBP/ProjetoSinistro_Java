package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.dto.AnaliseSaudeBucalRecordDto;
import br.com.fiap.ProjetoSinistro.repositorios.AnaliseSaudeBucalRepositorio;
import br.com.fiap.ProjetoSinistro.view.AnaliseSaudeBucalView;
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
public class AnaliseSaudeBucalController {

    @Autowired
    AnaliseSaudeBucalRepositorio analiseSaudeBucalRepositorio;

    @PostMapping("/analise_saude_bucal")
    public ResponseEntity<AnaliseSaudeBucalView> saveAnaliseSaudeBucal(@RequestBody @Valid AnaliseSaudeBucalRecordDto analiseSaudeBucalRecordDto) {
        var analiseSaudeBucalView = new AnaliseSaudeBucalView();
        BeanUtils.copyProperties(analiseSaudeBucalRecordDto, analiseSaudeBucalView);
        return ResponseEntity.status(HttpStatus.CREATED).body(analiseSaudeBucalRepositorio.save(analiseSaudeBucalView));
    }
    @GetMapping("/analise_saude_bucal")
    public ResponseEntity<List<AnaliseSaudeBucalView>> getAllAnaliseSaudeBucal(){
        List<AnaliseSaudeBucalView> analiseSaudeBucalList = analiseSaudeBucalRepositorio.findAll();
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
        Optional<AnaliseSaudeBucalView> analiseSaudeBucalO = analiseSaudeBucalRepositorio.findById(id);
        if (analiseSaudeBucalO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A análise bucal não foi encontrada.");
        }

        analiseSaudeBucalO.get().add(linkTo(methodOn(AnaliseSaudeBucalController.class).getAllAnaliseSaudeBucal()).withRel("Lista de Telefones"));


        return ResponseEntity.status(HttpStatus.OK).body(analiseSaudeBucalO.get());
    }
    @PutMapping("/analise_saude_bucal/{id}")
    public ResponseEntity<Object> updateAnaliseSaudeBucal(@PathVariable(value="id") UUID id, @RequestBody @Valid AnaliseSaudeBucalRecordDto analiseSaudeBucalRecordDto) {

        Optional<AnaliseSaudeBucalView> analiseSaudeBucalO = analiseSaudeBucalRepositorio.findById(id);
        if (analiseSaudeBucalO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A análise bucal não foi encontrada");
        }
        var AnaliseSaudeBucalView = analiseSaudeBucalO.get();
        BeanUtils.copyProperties(analiseSaudeBucalRecordDto, AnaliseSaudeBucalView);
        return ResponseEntity.status(HttpStatus.OK).body(analiseSaudeBucalRepositorio.save(AnaliseSaudeBucalView));
    }
    @DeleteMapping("/analise_saude_bucal/{id}")
    public ResponseEntity<Object> deleteAnaliseSaudeBucal(@PathVariable(value = "id") UUID id) {
        Optional<AnaliseSaudeBucalView> analiseSaudeBucalO = analiseSaudeBucalRepositorio.findById(id);
        if (analiseSaudeBucalO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A análise bucal não foi encontrada");
        }
        analiseSaudeBucalRepositorio.delete(analiseSaudeBucalO.get());
        return ResponseEntity.status(HttpStatus.OK).body("A análise bucal foi deletada com sucesso.");
    }
}
