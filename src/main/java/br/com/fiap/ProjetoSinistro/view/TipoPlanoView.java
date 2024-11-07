package br.com.fiap.ProjetoSinistro.view;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
//@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table (name = "TIPO_PLANO")
public class TipoPlanoView extends RepresentationModel <TipoPlanoView> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)

    private UUID id_tp_plano;

    private String nm_plano;

    public TipoPlanoView(UUID id_tp_plano, String nm_plano) {
        super();
        this.id_tp_plano = id_tp_plano;
        this.nm_plano = nm_plano;
    }

    public UUID getId_tp_plano() {
        return id_tp_plano;
    }

    public void setId_tp_plano(UUID id_tp_plano) {
        this.id_tp_plano = id_tp_plano;
    }

    public String getNm_plano() {
        return nm_plano;
    }

    public void setNm_plano(String nm_plano) {
        this.nm_plano = nm_plano;
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
