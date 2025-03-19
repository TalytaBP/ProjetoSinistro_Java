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
@Table(name = "PLANO")
@Data
public class PlanoView extends RepresentationModel<PlanoView> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_plano;

    @NotBlank
    private String tp_plano;

    @NotNull
    private int nr_carteirinha;

}