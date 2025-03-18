package br.com.fiap.ProjetoSinistro.view;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.ToString;

@ToString
@Entity
@Table(name = "ENDERECO")
public class EnderecoView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_endereco;

    @NotNull
    private int nr_cep;


    private int nr_complemento;

    @NotBlank
    private String nm_logradouro;

    @NotBlank
    private String nm_rua;

}