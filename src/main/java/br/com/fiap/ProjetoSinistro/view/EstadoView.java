package br.com.fiap.ProjetoSinistro.view;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
//@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "ESTADO")

public class EstadoView extends RepresentationModel<EstadoView> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id_estado;

    private String nm_estado;

    public EstadoView(UUID id_estado, String nm_estado) {
        super();
        this.id_estado = id_estado;
        this.nm_estado = nm_estado;
    }

    public UUID getId_estado() {
        return id_estado;
    }

    public void setId_estado(UUID id_estado) {
        this.id_estado = id_estado;
    }

    public String getNm_estado() {
        return nm_estado;
    }

    public void setNm_estado(String nm_estado) {
        this.nm_estado = nm_estado;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
