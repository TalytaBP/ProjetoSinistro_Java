package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TelefoneRecordDto(@NotBlank String tp_contato, @NotNull int nr_telefone, @NotNull int nr_ddd) {
}
