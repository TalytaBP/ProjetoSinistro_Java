package br.com.fiap.ProjetoSinistro.view;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.ToString;
import java.util.UUID;

@ToString
@Entity
@Table (name ="RESPONSAVEL_PACIENTE")
public class ResponsavelPacienteView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_responsavel;

    @NotBlank
    private String nm_completo;

    @NotNull
    private int nr_cpf;

    @NotNull
    private int nr_rg;

    @NotBlank
    private String tp_parentesco;

}