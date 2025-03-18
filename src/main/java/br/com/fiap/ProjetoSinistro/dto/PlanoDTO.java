package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class PlanoDTO {
    private UUID id_plano;

    @NotBlank
    private String tp_plano;

    @NotNull
    private int nr_carteirinha;
}
