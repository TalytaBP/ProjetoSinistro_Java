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
@Table (name = "SINTOMAS")
public class SintomasView extends RepresentationModel<SintomasView> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id_sintoma;

    private String tp_sintoma;

    public SintomasView(UUID id_sintoma, String tp_sintoma) {
        super();
        this.id_sintoma = id_sintoma;
        this.tp_sintoma = tp_sintoma;
    }

    public UUID getId_sintoma() {
        return id_sintoma;
    }

    public void setId_sintoma(UUID id_sintoma) {
        this.id_sintoma = id_sintoma;
    }

    public String getTp_sintoma() {
        return tp_sintoma;
    }

    public void setTp_sintoma(String tp_sintoma) {
        this.tp_sintoma = tp_sintoma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SintomasView that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id_sintoma, that.id_sintoma) && Objects.equals(tp_sintoma, that.tp_sintoma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_sintoma, tp_sintoma);
    }

    @Override
    public String toString() {
        return "SintomasView{" +
                "id_sintoma=" + id_sintoma +
                ", tp_sintoma='" + tp_sintoma + '\'' +
                '}';
    }
}
