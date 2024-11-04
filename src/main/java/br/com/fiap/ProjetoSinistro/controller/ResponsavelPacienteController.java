package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.dto.ResponsavelPacienteRecordDto;
import br.com.fiap.ProjetoSinistro.repositorios.ResponsavelPacienteRepositorio;
import br.com.fiap.ProjetoSinistro.view.ResponsavelPacienteView;
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
public class ResponsavelPacienteController {
    @Autowired
    ResponsavelPacienteRepositorio responsavelPacienteRepositorio;

    @PostMapping("/responsavel_paciente")
    public ResponseEntity<ResponsavelPacienteView> saveResponsavelPaciente(@RequestBody @Valid ResponsavelPacienteRecordDto responsavelPacienteRecordDto) {
        var responsavelPacienteView = new ResponsavelPacienteView();
        BeanUtils.copyProperties(responsavelPacienteRecordDto, responsavelPacienteView);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsavelPacienteRepositorio.save(responsavelPacienteView));
    }
    @GetMapping("/responsavel_paciente")
    public ResponseEntity<List<ResponsavelPacienteView>> getAllResponsavelPaciente(){
        List<ResponsavelPacienteView> responsavelPacienteList = responsavelPacienteRepositorio.findAll();
        if (!responsavelPacienteList.isEmpty()) {
            for (ResponsavelPacienteView responsavelPaciente : responsavelPacienteList) {
                UUID id = responsavelPaciente.getId_responsavel();
                responsavelPaciente.add(linkTo(methodOn(ResponsavelPacienteController.class).getOneResponsavelPaciente(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(responsavelPacienteList);
    }
    @GetMapping("/responsavel_paciente/{id}")
    public ResponseEntity<Object> getOneResponsavelPaciente(@PathVariable(value = "id") UUID id) {
        Optional<ResponsavelPacienteView> responsavelPacienteO = responsavelPacienteRepositorio.findById(id);
        if (responsavelPacienteO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Responsável não foi encontrado.");
        }

        responsavelPacienteO.get().add(linkTo(methodOn(ResponsavelPacienteController.class).getAllResponsavelPaciente()).withRel("Lista de Responsáveis"));


        return ResponseEntity.status(HttpStatus.OK).body(responsavelPacienteO.get());
    }
    @PutMapping("/responsavel_paciente/{id}")
    public ResponseEntity<Object> updateResponsavelPaciente(@PathVariable(value="id") UUID id, @RequestBody @Valid ResponsavelPacienteRecordDto responsavelPacienteRecordDto) {

        Optional<ResponsavelPacienteView> responsavelPacienteO = responsavelPacienteRepositorio.findById(id);
        if (responsavelPacienteO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Responsável não foi encontrado");
        }
        var ResponsavelPacienteView = responsavelPacienteO.get();
        BeanUtils.copyProperties(responsavelPacienteRecordDto, ResponsavelPacienteView);
        return ResponseEntity.status(HttpStatus.OK).body(responsavelPacienteRepositorio.save(ResponsavelPacienteView));
    }
    @DeleteMapping("/responsavel_paciente/{id}")
    public ResponseEntity<Object> deleteResponsavelPaciente(@PathVariable(value = "id") UUID id) {
        Optional<ResponsavelPacienteView> responsavelPacienteO = responsavelPacienteRepositorio.findById(id);
        if (responsavelPacienteO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O responsável não foi encontrado");
        }
        responsavelPacienteRepositorio.delete(responsavelPacienteO.get());
        return ResponseEntity.status(HttpStatus.OK).body("O responsável deletado com sucesso.");
    }
}
