package br.com.fiap.ProjetoSinistro.view;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@ToString
@Entity
@Table (name = "TIPO_PLANO")
@Data
public class TipoPlanoView extends RepresentationModel<TipoPlanoView> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_tp_plano;

    @NotBlank
    private String nm_plano;

}