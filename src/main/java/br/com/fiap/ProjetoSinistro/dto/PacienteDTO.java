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
public class PacienteDTO {
    private UUID id_paciente;

    @NotBlank
    private String nm_completo;

    @NotNull
    private int nr_idade;

    @NotBlank
    private String tp_sexo;

    @NotNull
    private int nr_cpf;

    @NotNull
    private int nr_rg;
}
