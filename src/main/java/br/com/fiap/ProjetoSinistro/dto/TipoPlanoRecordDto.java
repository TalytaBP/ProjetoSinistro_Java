package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;

public record TipoPlanoRecordDto(@NotBlank String nm_plano) {
}
