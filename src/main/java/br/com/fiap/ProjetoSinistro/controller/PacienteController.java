package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.dto.PacienteDTO;
import br.com.fiap.ProjetoSinistro.repositorios.PacienteRepository;
import br.com.fiap.ProjetoSinistro.view.PacienteView;
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
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;

    @PostMapping("/paciente")
    public ResponseEntity<PacienteView> savePaciente(@RequestBody @Valid PacienteDTO pacienteDTO) {
        var pacienteView = new PacienteView();
        BeanUtils.copyProperties(pacienteDTO, pacienteView);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteRepository.save(pacienteView));
    }
    @GetMapping("/paciente")
    public ResponseEntity<List<PacienteView>> getAllPaciente(){
        List<PacienteView> pacienteList = pacienteRepository.findAll();
        if (!pacienteList.isEmpty()) {
            for (PacienteView paciente : pacienteList) {
                UUID id = paciente.getId_paciente();
                paciente.add(linkTo(methodOn(PacienteController.class).getOnePaciente(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(pacienteList);
    }
    @GetMapping("/paciente/{id}")
    public ResponseEntity<Object> getOnePaciente(@PathVariable(value = "id") UUID id) {
        Optional<PacienteView> pacienteO = pacienteRepository.findById(id);
        if (pacienteO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O paciente não foi encontrado.");
        }

        pacienteO.get().add(linkTo(methodOn(PacienteController.class).getAllPaciente()).withRel("Lista de pacientes"));


        return ResponseEntity.status(HttpStatus.OK).body(pacienteO.get());
    }
    @PutMapping("/paciente/{id}")
    public ResponseEntity<Object> updatePaciente(@PathVariable(value="id") UUID id, @RequestBody @Valid PacienteDTO pacienteDTO) {

        Optional<PacienteView> pacienteO = pacienteRepository.findById(id);
        if (pacienteO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O paciente não foi encontrado");
        }
        var PacienteView = pacienteO.get();
        BeanUtils.copyProperties(pacienteDTO, PacienteView);
        return ResponseEntity.status(HttpStatus.OK).body(pacienteRepository.save(PacienteView));
    }
    @DeleteMapping("/paciente/{id}")
    public ResponseEntity<Object> deletePaciente(@PathVariable(value = "id") UUID id) {
        Optional<PacienteView> pacienteO = pacienteRepository.findById(id);
        if (pacienteO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O paciente não foi encontrado");
        }
        pacienteRepository.delete(pacienteO.get());
        return ResponseEntity.status(HttpStatus.OK).body("O paciente foi deletado com sucesso.");
    }

}
