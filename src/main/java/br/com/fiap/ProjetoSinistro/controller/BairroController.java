package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.dto.BairroRecordDto;
import br.com.fiap.ProjetoSinistro.repositorios.BairroRepositorio;
import br.com.fiap.ProjetoSinistro.view.BairroView;
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
public class BairroController {

    @Autowired
    BairroRepositorio bairroRepositorio;

    @PostMapping("/bairro")
    public ResponseEntity<BairroView> saveBairro(@RequestBody @Valid BairroRecordDto bairroRecordDto) {
        var bairroView = new BairroView();
        BeanUtils.copyProperties(bairroRecordDto, bairroView);
        return ResponseEntity.status(HttpStatus.CREATED).body(bairroRepositorio.save(bairroView));
    }
    @GetMapping("/bairro")
    public ResponseEntity<List<BairroView>> getAllBairro(){
        List<BairroView> bairroList = bairroRepositorio.findAll();
        if (!bairroList.isEmpty()) {
            for (BairroView bairro : bairroList) {
                UUID id = bairro.getId_bairro();
                bairro.add(linkTo(methodOn(BairroController.class).getOneBairro(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(bairroList);
    }
    @GetMapping("/bairro/{id}")
    public ResponseEntity<Object> getOneBairro(@PathVariable(value = "id") UUID id) {
        Optional<BairroView> bairroO = bairroRepositorio.findById(id);
        if (bairroO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O bairro não foi encontrado.");
        }

        bairroO.get().add(linkTo(methodOn(BairroController.class).getAllBairro()).withRel("Lista de bairros"));


        return ResponseEntity.status(HttpStatus.OK).body(bairroO.get());
    }
    @PutMapping("/bairro/{id}")
    public ResponseEntity<Object> updateBairro(@PathVariable(value="id") UUID id, @RequestBody @Valid BairroRecordDto bairroRecordDto) {

        Optional<BairroView> bairroO = bairroRepositorio.findById(id);
        if (bairroO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O bairro não foi encontrado");
        }
        var BairroView = bairroO.get();
        BeanUtils.copyProperties(bairroRecordDto, BairroView);
        return ResponseEntity.status(HttpStatus.OK).body(bairroRepositorio.save(BairroView));
    }
    @DeleteMapping("/bairro/{id}")
    public ResponseEntity<Object> deleteBairro(@PathVariable(value = "id") UUID id) {
        Optional<BairroView> bairroO = bairroRepositorio.findById(id);
        if (bairroO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O bairro não foi encontrado");
        }
        bairroRepositorio.delete(bairroO.get());
        return ResponseEntity.status(HttpStatus.OK).body("o bairro foi deletado com sucesso.");
    }
}
