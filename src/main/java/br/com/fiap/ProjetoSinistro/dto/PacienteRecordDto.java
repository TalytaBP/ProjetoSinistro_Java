package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PacienteRecordDto(@NotBlank String nm_completo, @NotNull int nr_idade, @NotBlank String tp_sexo, @NotNull int nr_cpf, @NotNull int nr_rg) {
}
