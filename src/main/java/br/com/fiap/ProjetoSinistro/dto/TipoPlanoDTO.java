package br.com.fiap.ProjetoSinistro.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoPlanoDTO {
    private UUID id_tp_plano;

    @NotBlank
    private String nm_plano;

}
