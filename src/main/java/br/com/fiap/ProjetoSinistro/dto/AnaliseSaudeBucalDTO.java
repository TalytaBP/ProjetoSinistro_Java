package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class AnaliseSaudeBucalDTO {
    private UUID id_scanner;

    @NotBlank
    private String qualidade_gengiva;

    @NotBlank
    private String qualidade_dente;

    private String coloracao_gengiva;

    @NotBlank
    private String coloracao_dente;

    @NotBlank
    private String sangramento;

    @NotBlank
    private String infeccao;
}
