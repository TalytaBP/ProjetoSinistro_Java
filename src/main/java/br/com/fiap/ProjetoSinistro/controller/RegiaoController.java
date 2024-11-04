package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.dto.RegiaoRecordDto;
import br.com.fiap.ProjetoSinistro.repositorios.RegiaoRepositorio;
import br.com.fiap.ProjetoSinistro.view.RegiaoView;
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
public class RegiaoController {

    @Autowired
    RegiaoRepositorio regiaoRepositorio;

    @PostMapping("/regiao")
    public ResponseEntity<RegiaoView> saveRegiao(@RequestBody @Valid RegiaoRecordDto regiaoRecordDto) {
        var regiaoView = new RegiaoView();
        BeanUtils.copyProperties(regiaoRecordDto, regiaoView);
        return ResponseEntity.status(HttpStatus.CREATED).body(regiaoRepositorio.save(regiaoView));
    }
    @GetMapping("/regiao")
    public ResponseEntity<List<RegiaoView>> getAllRegiao(){
        List<RegiaoView> regiaoList = regiaoRepositorio.findAll();
        if (!regiaoList.isEmpty()) {
            for (RegiaoView regiao : regiaoList) {
                UUID id = regiao.getId_regiao();
                regiao.add(linkTo(methodOn(RegiaoController.class).getOneRegiao(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(regiaoList);
    }
    @GetMapping("/regiao/{id}")
    public ResponseEntity<Object> getOneRegiao(@PathVariable(value = "id") UUID id) {
        Optional<RegiaoView> regiaoO = regiaoRepositorio.findById(id);
        if (regiaoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A região não foi encontrada");
        }

        regiaoO.get().add(linkTo(methodOn(RegiaoController.class).getAllRegiao()).withRel("Lista das Regiões"));


        return ResponseEntity.status(HttpStatus.OK).body(regiaoO.get());
    }
    @PutMapping("/regiao/{id}")
    public ResponseEntity<Object> updateRegiao(@PathVariable(value="id") UUID id, @RequestBody @Valid RegiaoRecordDto regiaoRecordDto) {

        Optional<RegiaoView> regiaoO = regiaoRepositorio.findById(id);
        if (regiaoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A região não foi encontrada");
        }
        var RegiaoView = regiaoO.get();
        BeanUtils.copyProperties(regiaoRecordDto, RegiaoView);
        return ResponseEntity.status(HttpStatus.OK).body(regiaoRepositorio.save(RegiaoView));
    }
    @DeleteMapping("/regiao/{id}")
    public ResponseEntity<Object> deleteRegiao(@PathVariable(value = "id") UUID id) {
        Optional<RegiaoView> regiaoO = regiaoRepositorio.findById(id);
        if (regiaoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A região não foi encontrado");
        }
        regiaoRepositorio.delete(regiaoO.get());
        return ResponseEntity.status(HttpStatus.OK).body("A região foi deletada com sucesso.");
    }
}
