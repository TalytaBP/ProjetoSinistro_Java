package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;

public record ResultadoRecordDto(@NotBlank String nm_doenca, @NotBlank String tp_gravidade) {
}
