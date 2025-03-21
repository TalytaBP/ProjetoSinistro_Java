package br.com.fiap.ProjetoSinistro.controller;

import br.com.fiap.ProjetoSinistro.dto.MunicipioDTO;
import br.com.fiap.ProjetoSinistro.repositorios.MunicipioRepository;
import br.com.fiap.ProjetoSinistro.view.MunicipioView;
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
public class MunicipioController {

    @Autowired
    MunicipioRepository municipioRepository;

    @PostMapping("/municipio")
    public ResponseEntity<MunicipioView> saveMunicipio(@RequestBody @Valid MunicipioDTO municipioDTO) {
        var municipioView = new MunicipioView();
        BeanUtils.copyProperties(municipioDTO, municipioView);
        return ResponseEntity.status(HttpStatus.CREATED).body(municipioRepository.save(municipioView));
    }
    @GetMapping("/municipio")
    public ResponseEntity<List<MunicipioView>> getAllMunicipio(){
        List<MunicipioView> municipioList = municipioRepository.findAll();
        if (!municipioList.isEmpty()) {
            for (MunicipioView municipio : municipioList) {
                UUID id = municipio.getId_municipio();
                municipio.add(linkTo(methodOn(MunicipioController.class).getOneMunicipio(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(municipioList);
    }
    @GetMapping("/municipio/{id}")
    public ResponseEntity<Object> getOneMunicipio(@PathVariable(value = "id") UUID id) {
        Optional<MunicipioView> municipioO = municipioRepository.findById(id);
        if (municipioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O municipio não foi encontrado.");
        }

        municipioO.get().add(linkTo(methodOn(MunicipioController.class).getAllMunicipio()).withRel("Lista de municipios"));


        return ResponseEntity.status(HttpStatus.OK).body(municipioO.get());
    }
    @PutMapping("/municipio/{id}")
    public ResponseEntity<Object> updateMunicipio(@PathVariable(value="id") UUID id, @RequestBody @Valid MunicipioDTO municipioDTO) {

        Optional<MunicipioView> municipioO = municipioRepository.findById(id);
        if (municipioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O municipio não foi encontrado");
        }
        var MunicipioView = municipioO.get();
        BeanUtils.copyProperties(municipioDTO, MunicipioView);
        return ResponseEntity.status(HttpStatus.OK).body(municipioRepository.save(MunicipioView));
    }
    @DeleteMapping("/municipio/{id}")
    public ResponseEntity<Object> deleteMunicipio(@PathVariable(value = "id") UUID id) {
        Optional<MunicipioView> municipioO = municipioRepository.findById(id);
        if (municipioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O municipio não foi encontrado");
        }
        municipioRepository.delete(municipioO.get());
        return ResponseEntity.status(HttpStatus.OK).body("o municipio foi deletado com sucesso.");
    }

}
