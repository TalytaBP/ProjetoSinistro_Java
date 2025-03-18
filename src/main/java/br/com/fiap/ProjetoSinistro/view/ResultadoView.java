package br.com.fiap.ProjetoSinistro.view;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.ToString;
import java.util.UUID;

@ToString
@Entity
@Table (name = "RESULTADO")
public class ResultadoView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_doenca;

    @NotBlank
    private String nm_doenca;

    @NotBlank
    private String tp_gravidade;

}