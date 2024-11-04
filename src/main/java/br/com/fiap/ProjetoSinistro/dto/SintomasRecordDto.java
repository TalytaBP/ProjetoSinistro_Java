package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;

public record SintomasRecordDto(@NotBlank String tp_sintoma) {
}
