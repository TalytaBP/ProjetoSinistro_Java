package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.dto.UsuarioRecordDto;
import br.com.fiap.ProjetoSinistro.repositorios.UsuarioRepositorio;
import br.com.fiap.ProjetoSinistro.view.UsuarioView;
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
public class UsuarioController {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioView> saveBrinquedo(@RequestBody @Valid UsuarioRecordDto usuarioRecordDto) {
        var usuarioView = new UsuarioView();
        BeanUtils.copyProperties(usuarioRecordDto, usuarioView);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepositorio.save(usuarioView));
    }
    @GetMapping("/usuario")
    public ResponseEntity<List<UsuarioView>> getAllUsuario(){
        List<UsuarioView> usuarioList = usuarioRepositorio.findAll();
        if (!usuarioList.isEmpty()) {
            for (UsuarioView usuario : usuarioList) {
                UUID id = usuario.getId_usuario();
                usuario.add(linkTo(methodOn(UsuarioController.class).getOneUsuario(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioList);
    }
    @GetMapping("/usuario/{id}")
    public ResponseEntity<Object> getOneUsuario(@PathVariable(value = "id") UUID id) {
        Optional<UsuarioView> usuarioO = usuarioRepositorio.findById(id);
        if (usuarioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O usuário não foi encontrado.");
        }

        usuarioO.get().add(linkTo(methodOn(UsuarioController.class).getAllUsuario()).withRel("Lista de Usuario"));


        return ResponseEntity.status(HttpStatus.OK).body(usuarioO.get());
    }
    @PutMapping("/usuario/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value="id") UUID id, @RequestBody @Valid UsuarioRecordDto usuarioRecordDto) {

        Optional<UsuarioView> usuarioO = usuarioRepositorio.findById(id);
        if (usuarioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O usuário não foi encontrado");
        }
        var UsuarioView = usuarioO.get();
        BeanUtils.copyProperties(usuarioRecordDto, UsuarioView);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepositorio.save(UsuarioView));
    }
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id") UUID id) {
        Optional<UsuarioView> usuarioO = usuarioRepositorio.findById(id);
        if (usuarioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O usuário não foi encontrado");
        }
        usuarioRepositorio.delete(usuarioO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso.");
    }


}
