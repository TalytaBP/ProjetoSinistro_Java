package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoDTO {
    private UUID id_doenca;

    @NotBlank
    private String nm_doenca;

    @NotBlank
    private String tp_gravidade;

}
