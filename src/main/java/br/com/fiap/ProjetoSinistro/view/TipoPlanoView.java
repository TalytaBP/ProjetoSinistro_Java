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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TipoPlanoView that = (TipoPlanoView) o;
        return Objects.equals(id_tp_plano, that.id_tp_plano) && Objects.equals(nm_plano, that.nm_plano);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_tp_plano, nm_plano);
    }

    @Override
    public String toString() {
        return "TipoPlanoView{" +
                "id_tp_plano=" + id_tp_plano +
                ", nm_plano='" + nm_plano + '\'' +
                '}';
    }
}
