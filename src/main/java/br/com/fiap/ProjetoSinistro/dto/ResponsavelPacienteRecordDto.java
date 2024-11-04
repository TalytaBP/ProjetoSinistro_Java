package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponsavelPacienteRecordDto(@NotBlank String nm_completo, @NotNull int nr_cpf, @NotNull int nr_rg, @NotBlank String tp_parentesco) {
}
