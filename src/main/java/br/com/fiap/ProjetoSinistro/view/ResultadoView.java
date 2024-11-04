package br.com.fiap.ProjetoSinistro.view;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.service.UnknownUnwrapTypeException;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
//s@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table (name = "RESULTADO")
public class ResultadoView extends RepresentationModel <ResultadoView> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id_doenca;

    private String nm_doenca;

    private String tp_gravidade;

    public ResultadoView(UUID id_doenca, String nm_doenca, String tp_gravidade) {
        super();
        this.id_doenca = id_doenca;
        this.nm_doenca = nm_doenca;
        this.tp_gravidade = tp_gravidade;
    }

    public UUID getId_doenca() {
        return id_doenca;
    }

    public void setId_doenca(UUID id_doenca) {
        this.id_doenca = id_doenca;
    }

    public String getNm_doenca() {
        return nm_doenca;
    }

    public void setNm_doenca(String nm_doenca) {
        this.nm_doenca = nm_doenca;
    }

    public String getTp_gravidade() {
        return tp_gravidade;
    }

    public void setTp_gravidade(String tp_gravidade) {
        this.tp_gravidade = tp_gravidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultadoView that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id_doenca, that.id_doenca) && Objects.equals(nm_doenca, that.nm_doenca) && Objects.equals(tp_gravidade, that.tp_gravidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_doenca, nm_doenca, tp_gravidade);
    }

    @Override
    public String toString() {
        return "ResultadoView{" +
                "id_doenca=" + id_doenca +
                ", nm_doenca='" + nm_doenca + '\'' +
                ", tp_gravidade='" + tp_gravidade + '\'' +
                '}';
    }
}
