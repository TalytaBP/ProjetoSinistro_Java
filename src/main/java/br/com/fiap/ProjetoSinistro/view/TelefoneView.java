package br.com.fiap.ProjetoSinistro.view;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.ToString;
import java.util.UUID;

@ToString
@Entity
@Table (name = "TELEFONE")
public class TelefoneView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_telefone;

    @NotBlank
    private String tp_contato;

    @NotNull
    private int nr_telefone;

    @NotNull
    private int nr_ddd;

}