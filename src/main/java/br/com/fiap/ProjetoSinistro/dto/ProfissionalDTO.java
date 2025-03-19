package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalDTO {
    private UUID id_profissional;

    @NotBlank
    private String especialista;

    @NotBlank
    private String nm_completo;

}
