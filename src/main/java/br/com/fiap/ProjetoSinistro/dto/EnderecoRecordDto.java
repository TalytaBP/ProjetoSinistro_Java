package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoRecordDto(@NotNull int nr_cep, @NotNull int nr_complemento, @NotBlank String nm_logradouro, @NotBlank String nm_rua) {
}
