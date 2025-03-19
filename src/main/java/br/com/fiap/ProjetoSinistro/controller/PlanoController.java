package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.dto.PlanoDTO;
import br.com.fiap.ProjetoSinistro.repositorios.PlanoRepository;
import br.com.fiap.ProjetoSinistro.view.PlanoView;
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
public class PlanoController {

    @Autowired
    PlanoRepository planoRepository;

    @PostMapping("/plano")
    public ResponseEntity<PlanoView> savePlano(@RequestBody @Valid PlanoDTO planoDTO) {
        var planoView = new PlanoView();
        BeanUtils.copyProperties(planoDTO, planoView);
        return ResponseEntity.status(HttpStatus.CREATED).body(planoRepository.save(planoView));
    }
    @GetMapping("/plano")
    public ResponseEntity<List<PlanoView>> getAllPlano(){
        List<PlanoView> planoList = planoRepository.findAll();
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
        Optional<PlanoView> planoO = planoRepository.findById(id);
        if (planoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O plano não foi encontrado");
        }

        planoO.get().add(linkTo(methodOn(PlanoController.class).getAllPlano()).withRel("Lista de planos"));


        return ResponseEntity.status(HttpStatus.OK).body(planoO.get());
    }
    @PutMapping("/plano/{id}")
    public ResponseEntity<Object> updatePlano(@PathVariable(value="id") UUID id, @RequestBody @Valid PlanoDTO planoDTO) {

        Optional<PlanoView> planoO = planoRepository.findById(id);
        if (planoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O plano não foi encontrado");
        }
        var TelefoneView = planoO.get();
        Object PlanoView = null;
        BeanUtils.copyProperties(planoDTO, PlanoView);
        return ResponseEntity.status(HttpStatus.OK).body(planoRepository.save(TelefoneView));
    }
    @DeleteMapping("/plano/{id}")
    public ResponseEntity<Object> deletePlano(@PathVariable(value = "id") UUID id) {
        Optional<PlanoView> planoO = planoRepository.findById(id);
        if (planoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O plano não foi encontrado");
        }
        planoRepository.delete(planoO.get());
        return ResponseEntity.status(HttpStatus.OK).body("O plano foi deletado com sucesso.");
    }
}
