package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.repositorios.TipoPlanoRepository;
import br.com.fiap.ProjetoSinistro.view.TipoPlanoView;
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
public class TipoPlanoController {

    @Autowired
    TipoPlanoRepository tipoPlanoRepositorio;

    @PostMapping("/tipo_plano")
    public ResponseEntity<TipoPlanoView> saveTipoPlano(@RequestBody @Valid TipoPlanoRecordDto tipoPlanoRecordDto) {
        var tipoPlanoView = new TipoPlanoView();
        BeanUtils.copyProperties(tipoPlanoRecordDto, tipoPlanoView);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoPlanoRepositorio.save(tipoPlanoView));
    }
    @GetMapping("/tipo_plano")
    public ResponseEntity<List<TipoPlanoView>> getAllTipoPlano(){
        List<TipoPlanoView> tipoPlanoList = tipoPlanoRepositorio.findAll();
        if (!tipoPlanoList.isEmpty()) {
            for (TipoPlanoView tipoPlano : tipoPlanoList) {
                UUID id = tipoPlano.getId_tp_plano();
                tipoPlano.add(linkTo(methodOn(TipoPlanoController.class).getOneTipoPlano(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(tipoPlanoList);
    }
    @GetMapping("/tipo_plano/{id}")
    public ResponseEntity<Object> getOneTipoPlano(@PathVariable(value = "id") UUID id) {
        Optional<TipoPlanoView> tipoPlanoO = tipoPlanoRepositorio.findById(id);
        if (tipoPlanoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O tipo de plano não foi encontrado.");
        }

        tipoPlanoO.get().add(linkTo(methodOn(TipoPlanoController.class).getAllTipoPlano()).withRel("Lista de tiplo de plano"));


        return ResponseEntity.status(HttpStatus.OK).body(tipoPlanoO.get());
    }
    @PutMapping("/tipo_plano/{id}")
    public ResponseEntity<Object> updateTipoPlano(@PathVariable(value="id") UUID id, @RequestBody @Valid TipoPlanoRecordDto tipoPlanoRecordDto) {

        Optional<TipoPlanoView> tipoPlanoO = tipoPlanoRepositorio.findById(id);
        if (tipoPlanoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O tipo de plano não foi encontrado");
        }
        var TipoPlanoView = tipoPlanoO.get();
        BeanUtils.copyProperties(tipoPlanoRecordDto, TipoPlanoView);
        return ResponseEntity.status(HttpStatus.OK).body(tipoPlanoRepositorio.save(TipoPlanoView));
    }
    @DeleteMapping("/tipo_plano/{id}")
    public ResponseEntity<Object> deleteTipoPlano(@PathVariable(value = "id") UUID id) {
        Optional<TipoPlanoView> tipoPlanoO = tipoPlanoRepositorio.findById(id);
        if (tipoPlanoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O tipo de plano não foi encontrado");
        }
        tipoPlanoRepositorio.delete(tipoPlanoO.get());
        return ResponseEntity.status(HttpStatus.OK).body("O tipo de plano foi deletado com sucesso.");
    }

}
