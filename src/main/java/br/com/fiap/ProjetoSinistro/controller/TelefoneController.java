package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.dto.TelefoneDTO;
import br.com.fiap.ProjetoSinistro.repositorios.TelefoneRepository;
import br.com.fiap.ProjetoSinistro.view.TelefoneView;
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
public class TelefoneController {

    @Autowired
    TelefoneRepository telefoneRepository;

    @PostMapping("/telefone")
    public ResponseEntity<TelefoneView> saveTelefone(@RequestBody @Valid TelefoneDTO telefoneDTO) {
        var telefoneView = new TelefoneView();
        BeanUtils.copyProperties(telefoneDTO, telefoneView);
        return ResponseEntity.status(HttpStatus.CREATED).body(telefoneRepository.save(telefoneView));
    }
    @GetMapping("/telefone")
    public ResponseEntity<List<TelefoneView>> getAllTelefone(){
        List<TelefoneView> telefoneList = telefoneRepository.findAll();
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
        Optional<TelefoneView> telefoneO = telefoneRepository.findById(id);
        if (telefoneO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Telefone não foi encontrado.");
        }

        telefoneO.get().add(linkTo(methodOn(TelefoneController.class).getAllTelefone()).withRel("Lista de Telefones"));


        return ResponseEntity.status(HttpStatus.OK).body(telefoneO.get());
    }
    @PutMapping("/telefone/{id}")
    public ResponseEntity<Object> updateTelefone(@PathVariable(value="id") UUID id, @RequestBody @Valid TelefoneDTO telefoneDTO) {

        Optional<TelefoneView> telefoneO = telefoneRepository.findById(id);
        if (telefoneO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Telefone não foi encontrado");
        }
        var TelefoneView = telefoneO.get();
        BeanUtils.copyProperties(telefoneDTO, TelefoneView);
        return ResponseEntity.status(HttpStatus.OK).body(telefoneRepository.save(TelefoneView));
    }
    @DeleteMapping("/telefone/{id}")
    public ResponseEntity<Object> deleteTelefone(@PathVariable(value = "id") UUID id) {
        Optional<TelefoneView> telefoneO = telefoneRepository.findById(id);
        if (telefoneO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Telefone não foi encontrado");
        }
        telefoneRepository.delete(telefoneO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Telefone deletado com sucesso.");
    }

}
