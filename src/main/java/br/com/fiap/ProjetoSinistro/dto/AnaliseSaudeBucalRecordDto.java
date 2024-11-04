package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;

public record AnaliseSaudeBucalRecordDto(@NotBlank String qualidade_gengiva, @NotBlank String qualidade_dente, @NotBlank String coloracao_gengiva, @NotBlank String coloracao_dente, @NotBlank String sangramento, @NotBlank String infeccao) {
}
