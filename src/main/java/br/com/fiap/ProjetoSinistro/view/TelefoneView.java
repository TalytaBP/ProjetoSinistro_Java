package br.com.fiap.ProjetoSinistro.view;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@ToString
@Entity
@Table (name = "TELEFONE")
@Data
public class TelefoneView extends RepresentationModel<TelefoneView> {

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