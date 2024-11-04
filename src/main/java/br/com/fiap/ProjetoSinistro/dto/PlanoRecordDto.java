package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlanoRecordDto(@NotBlank String tp_plano, @NotNull int nr_carteirinha) {
}
