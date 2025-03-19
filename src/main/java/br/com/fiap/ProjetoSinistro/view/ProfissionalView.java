package br.com.fiap.ProjetoSinistro.view;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@ToString
@Entity
@Table (name = "PROFISSIONAL")
@Data
public class ProfissionalView extends RepresentationModel<ProfissionalView> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_profissional;

    @NotBlank
    private String especialista;

    @NotBlank
    private String nm_completo;

}