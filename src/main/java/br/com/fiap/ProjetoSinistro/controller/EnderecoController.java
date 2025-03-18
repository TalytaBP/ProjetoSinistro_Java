package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.dto.EnderecoDTO;
import br.com.fiap.ProjetoSinistro.repositorios.EnderecoRepository;
import br.com.fiap.ProjetoSinistro.view.EnderecoView;
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
public class EnderecoController {

    @Autowired
    EnderecoRepository enderecoRepository;

    @PostMapping("/endereco")
    public ResponseEntity<EnderecoView> saveEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO) {
        var enderecoView = new EnderecoView();
        BeanUtils.copyProperties(enderecoDTO, enderecoView);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoRepository.save(enderecoView));
    }
    @GetMapping("/endereco")
    public ResponseEntity<List<EnderecoView>> getAllEndereco(){
        List<EnderecoView> enderecoList = enderecoRepository.findAll();
        if (!enderecoList.isEmpty()) {
            for (EnderecoView endereco : enderecoList) {
                UUID id = endereco.getId_endereco();
                endereco.add(linkTo(methodOn(EnderecoController.class).getOneEndereco(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(enderecoList);
    }
    @GetMapping("/endereco/{id}")
    public ResponseEntity<Object> getOneEndereco(@PathVariable(value = "id") UUID id) {
        Optional<EnderecoView> enderecoO = enderecoRepository.findById(id);
        if (enderecoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O endereço não foi encontrado.");
        }

        enderecoO.get().add(linkTo(methodOn(EnderecoController.class).getAllEndereco()).withRel("Lista de Endereços"));


        return ResponseEntity.status(HttpStatus.OK).body(enderecoO.get());
    }
    @PutMapping("/endereco/{id}")
    public ResponseEntity<Object> updateEndereco(@PathVariable(value="id") UUID id, @RequestBody @Valid EnderecoDTO enderecoDTO) {

        Optional<EnderecoView> enderecoO = enderecoRepository.findById(id);
        if (enderecoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O endereco não foi encontrado");
        }
        var EnderecoView = enderecoO.get();
        BeanUtils.copyProperties(enderecoDTO, EnderecoView);
        return ResponseEntity.status(HttpStatus.OK).body(enderecoRepository.save(EnderecoView));
    }
    @DeleteMapping("/endereco/{id}")
    public ResponseEntity<Object> deleteEndereco(@PathVariable(value = "id") UUID id) {
        Optional<EnderecoView> enderecoO = enderecoRepository.findById(id);
        if (enderecoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O endereco não foi encontrado");
        }
        enderecoRepository.delete(enderecoO.get());
        return ResponseEntity.status(HttpStatus.OK).body("O endereco foi deletado com sucesso.");
    }

}
