package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class EstadoDTO {
    private UUID id_estado;

    @NotBlank
    private String nm_estado;

}
