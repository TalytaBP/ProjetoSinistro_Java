package br.com.fiap.ProjetoSinistro.view;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@ToString
@Entity
@Table(name = "PACIENTE")
@Data
public class PacienteView extends RepresentationModel<PacienteView> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_paciente;

    @NotBlank
    private String nm_completo;

    @NotNull
    private int nr_idade;

    @NotBlank
    private String tp_sexo;

    @NotNull
    private int nr_cpf;

    @NotNull
    private int nr_rg;

}