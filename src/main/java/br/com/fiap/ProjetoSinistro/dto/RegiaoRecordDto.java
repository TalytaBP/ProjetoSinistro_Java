package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;

public record RegiaoRecordDto(@NotBlank String nm_regiao) {
}
