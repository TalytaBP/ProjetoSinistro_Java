package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.dto.TelefoneRecordDto;
import br.com.fiap.ProjetoSinistro.repositorios.TelefoneRepositorio;
import br.com.fiap.ProjetoSinistro.view.TelefoneView;
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
public class TelefoneController {

    @Autowired
    TelefoneRepositorio telefoneRepositorio;

    @PostMapping("/telefone")
    public ResponseEntity<TelefoneView> saveTelefone(@RequestBody @Valid TelefoneRecordDto telefoneRecordDto) {
        var telefoneView = new TelefoneView();
        BeanUtils.copyProperties(telefoneRecordDto, telefoneView);
        return ResponseEntity.status(HttpStatus.CREATED).body(telefoneRepositorio.save(telefoneView));
    }
    @GetMapping("/telefone")
    public ResponseEntity<List<TelefoneView>> getAllTelefone(){
        List<TelefoneView> telefoneList = telefoneRepositorio.findAll();
        if (!telefoneList.isEmpty()) {
            for (TelefoneView telefone : telefoneList) {
                UUID id = telefone.getId_telefone();
                telefone.add(linkTo(methodOn(TelefoneController.class).getOneTelefone(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(telefoneList);
    }
    @GetMapping("/telefone/{id}")
    public ResponseEntity<Object> getOneTelefone(@PathVariable(value = "id") UUID id) {
        Optional<TelefoneView> telefoneO = telefoneRepositorio.findById(id);
        if (telefoneO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Telefone não foi encontrado.");
        }

        telefoneO.get().add(linkTo(methodOn(TelefoneController.class).getAllTelefone()).withRel("Lista de Telefones"));


        return ResponseEntity.status(HttpStatus.OK).body(telefoneO.get());
    }
    @PutMapping("/telefone/{id}")
    public ResponseEntity<Object> updateTelefone(@PathVariable(value="id") UUID id, @RequestBody @Valid TelefoneRecordDto telefoneRecordDto) {

        Optional<TelefoneView> telefoneO = telefoneRepositorio.findById(id);
        if (telefoneO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Telefone não foi encontrado");
        }
        var TelefoneView = telefoneO.get();
        BeanUtils.copyProperties(telefoneRecordDto, TelefoneView);
        return ResponseEntity.status(HttpStatus.OK).body(telefoneRepositorio.save(TelefoneView));
    }
    @DeleteMapping("/telefone/{id}")
    public ResponseEntity<Object> deleteTelefone(@PathVariable(value = "id") UUID id) {
        Optional<TelefoneView> telefoneO = telefoneRepositorio.findById(id);
        if (telefoneO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Telefone não foi encontrado");
        }
        telefoneRepositorio.delete(telefoneO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Telefone deletado com sucesso.");
    }

}
