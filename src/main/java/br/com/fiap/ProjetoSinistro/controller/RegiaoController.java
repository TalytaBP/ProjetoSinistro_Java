package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.dto.RegiaoDTO;
import br.com.fiap.ProjetoSinistro.dto.ResultadoDTO;
import br.com.fiap.ProjetoSinistro.repositorios.RegiaoRepository;
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
    RegiaoRepository regiaoRepository;

    @PostMapping("/regiao")
    public ResponseEntity<RegiaoView> saveRegiao(@RequestBody @Valid RegiaoDTO regiaoDto) {
        var regiaoView = new RegiaoView();
        BeanUtils.copyProperties(regiaoDto, regiaoView);
        return ResponseEntity.status(HttpStatus.CREATED).body(regiaoRepository.save(regiaoView));
    }
    @GetMapping("/regiao")
    public ResponseEntity<List<RegiaoView>> getAllRegiao(){
        List<RegiaoView> regiaoList = regiaoRepository.findAll();
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
        Optional<RegiaoView> regiaoO = regiaoRepository.findById(id);
        if (regiaoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A região não foi encontrada");
        }

        regiaoO.get().add(linkTo(methodOn(RegiaoController.class).getAllRegiao()).withRel("Lista das Regiões"));


        return ResponseEntity.status(HttpStatus.OK).body(regiaoO.get());
    }
    @PutMapping("/regiao/{id}")
    public ResponseEntity<Object> updateRegiao(@PathVariable(value="id") UUID id, @RequestBody @Valid RegiaoDTO regiaoDTO) {

        Optional<RegiaoView> regiaoO = regiaoRepository.findById(id);
        if (regiaoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A região não foi encontrada");
        }
        var RegiaoView = regiaoO.get();
        BeanUtils.copyProperties(regiaoDTO, RegiaoView);
        return ResponseEntity.status(HttpStatus.OK).body(regiaoRepository.save(RegiaoView));
    }
    @DeleteMapping("/regiao/{id}")
    public ResponseEntity<Object> deleteRegiao(@PathVariable(value = "id") UUID id) {
        Optional<RegiaoView> regiaoO = regiaoRepository.findById(id);
        if (regiaoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A região não foi encontrado");
        }
        regiaoRepository.delete(regiaoO.get());
        return ResponseEntity.status(HttpStatus.OK).body("A região foi deletada com sucesso.");
    }
}
