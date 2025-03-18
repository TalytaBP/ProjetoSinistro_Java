package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.dto.ResultadoDTO;
import br.com.fiap.ProjetoSinistro.repositorios.ResultadoRepository;
import br.com.fiap.ProjetoSinistro.view.ResultadoView;
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
public class ResultadoController {

    @Autowired
    ResultadoRepository resultadoRepository;

    @PostMapping("/resultado")
    public ResponseEntity<ResultadoView> saveResultado(@RequestBody @Valid ResultadoDTO resultadoDTO) {
        var resultadoView = new ResultadoView();
        BeanUtils.copyProperties(resultadoDTO, resultadoView);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultadoRepository.save(resultadoView));
    }
    @GetMapping("/resultado")
    public ResponseEntity<List<ResultadoView>> getAllResultado(){
        List<ResultadoView> resultadoList = resultadoRepository.findAll();
        if (!resultadoList.isEmpty()) {
            for (ResultadoView resultado : resultadoList) {
                UUID id = resultado.getId_doenca();
                resultado.add(linkTo(methodOn(ResultadoController.class).getOneResultado(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(resultadoList);
    }
    @GetMapping("/resultado/{id}")
    public ResponseEntity<Object> getOneResultado(@PathVariable(value = "id") UUID id) {
        Optional<ResultadoView> resultadoO = resultadoRepository.findById(id);
        if (resultadoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O resultado não foi encontrado.");
        }

        resultadoO.get().add(linkTo(methodOn(ResultadoController.class).getAllResultado()).withRel("Lista de resultado"));


        return ResponseEntity.status(HttpStatus.OK).body(resultadoO.get());
    }
    @PutMapping("/resultado/{id}")
    public ResponseEntity<Object> updateResultado(@PathVariable(value="id") UUID id, @RequestBody @Valid ResultadoDTO resultadoDTO) {

        Optional<ResultadoView> resultadoO = resultadoRepository.findById(id);
        if (resultadoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O resultado não foi encontrado");
        }
        var ResultadoView = resultadoO.get();
        BeanUtils.copyProperties(resultadoDTO, ResultadoView);
        return ResponseEntity.status(HttpStatus.OK).body(resultadoRepository.save(ResultadoView));
    }
    @DeleteMapping("/resultado/{id}")
    public ResponseEntity<Object> deleteResultado(@PathVariable(value = "id") UUID id) {
        Optional<ResultadoView> resultadoO = resultadoRepository.findById(id);
        if (resultadoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O resultado não foi encontrado");
        }
        resultadoRepository.delete(resultadoO.get());
        return ResponseEntity.status(HttpStatus.OK).body("O resultado foi deletado com sucesso.");
    }
}
