package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;

public record BairroRecordDto(@NotBlank String nm_bairro) {
}
