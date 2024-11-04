package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;

public record EstadoRecordDto(@NotBlank String nm_estado) {
}
