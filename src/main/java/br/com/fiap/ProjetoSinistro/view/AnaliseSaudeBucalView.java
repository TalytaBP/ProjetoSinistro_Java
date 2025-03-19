package br.com.fiap.ProjetoSinistro.view;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.Data;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@ToString
@Entity
@Table(name = "ANALISE_SAUDE_BUCAL")
@Data
public class AnaliseSaudeBucalView extends RepresentationModel<AnaliseSaudeBucalView> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_scanner;

    @NotBlank
    private String qualidade_gengiva;

    @NotBlank
    private String qualidade_dente;

    private String coloracao_gengiva;

    @NotBlank
    private String coloracao_dente;

    @NotBlank
    private String sangramento;

    @NotBlank
    private String infeccao;



}
