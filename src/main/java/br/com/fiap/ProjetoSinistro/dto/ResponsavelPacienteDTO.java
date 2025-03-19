package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsavelPacienteDTO {
    private UUID id_responsavel;

    @NotBlank
    private String nm_completo;

    @NotNull
    private int nr_cpf;

    @NotNull
    private int nr_rg;

    @NotBlank
    private String tp_parentesco;

}
