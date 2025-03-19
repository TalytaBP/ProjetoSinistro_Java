package br.com.fiap.ProjetoSinistro.view;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

import lombok.Data;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@ToString
@Entity
@Table(name = "ESTADO")
@Data
public class EstadoView extends RepresentationModel<EstadoView> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_estado;

    @NotBlank
    private String nm_estado;


}
