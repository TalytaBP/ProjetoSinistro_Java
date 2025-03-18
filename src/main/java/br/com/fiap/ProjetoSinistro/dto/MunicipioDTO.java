package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class MunicipioDTO {
    private UUID id_municipio;

    @NotBlank
    private String nm_municipio;

}
