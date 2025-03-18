package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class TelefoneDTO {
    private UUID id_telefone;

    @NotBlank
    private String tp_contato;

    @NotNull
    private int nr_telefone;

    @NotNull
    private int nr_ddd;
}
