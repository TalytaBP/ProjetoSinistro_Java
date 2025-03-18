package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {
    private UUID id_endereco;

    @NotNull
    private int nr_cep;


    private int nr_complemento;

    @NotBlank
    private String nm_logradouro;

    @NotBlank
    private String nm_rua;
}
