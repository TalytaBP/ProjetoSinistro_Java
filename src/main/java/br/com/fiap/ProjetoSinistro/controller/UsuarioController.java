package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.dto.UsuarioDTO;
import br.com.fiap.ProjetoSinistro.repositorios.UsuarioRepository;
import br.com.fiap.ProjetoSinistro.service.UsuarioService;
import br.com.fiap.ProjetoSinistro.view.UsuarioView;
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
public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping("/usuario")
    public ResponseEntity<UsuarioView> saveBrinquedo(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        var usuarioView = new UsuarioView();
        BeanUtils.copyProperties(usuarioDTO, usuarioView);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuarioView));
    }
    @GetMapping("/usuario")
    public ResponseEntity<List<UsuarioView>> getAllUsuario(){
        List<UsuarioView> usuarioList = usuarioRepository.findAll();
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
        Optional<UsuarioView> usuarioO = usuarioRepository.findById(id);
        if (usuarioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O usuário não foi encontrado.");
        }

        usuarioO.get().add(linkTo(methodOn(UsuarioController.class).getAllUsuario()).withRel("Lista de Usuario"));


        return ResponseEntity.status(HttpStatus.OK).body(usuarioO.get());
    }
    @PutMapping("/usuario/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value="id") UUID id, @RequestBody @Valid UsuarioDTO usuarioDTO) {

        Optional<UsuarioView> usuarioO = usuarioRepository.findById(id);
        if (usuarioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O usuário não foi encontrado");
        }
        var UsuarioView = usuarioO.get();
        BeanUtils.copyProperties(usuarioDTO, UsuarioView);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(UsuarioView));
    }
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id") UUID id) {
        Optional<UsuarioView> usuarioO = usuarioRepository.findById(id);
        if (usuarioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O usuário não foi encontrado");
        }
        usuarioRepository.delete(usuarioO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso.");
    }


}
