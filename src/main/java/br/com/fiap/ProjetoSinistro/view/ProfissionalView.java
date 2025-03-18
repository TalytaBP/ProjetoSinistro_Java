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
@Table (name = "PROFISSIONAL")
public class ProfissionalView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_profissional;

    @NotBlank
    private String especialista;

    @NotBlank
    private String nm_completo;

}