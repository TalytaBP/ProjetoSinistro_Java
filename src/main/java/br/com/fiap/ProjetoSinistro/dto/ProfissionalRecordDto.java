package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;

public record ProfissionalRecordDto(@NotBlank String especialista, @NotBlank String nm_completo) {
}
