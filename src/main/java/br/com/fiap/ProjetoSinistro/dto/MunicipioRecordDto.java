package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;

public record MunicipioRecordDto(@NotBlank String nm_municipio) {
}
