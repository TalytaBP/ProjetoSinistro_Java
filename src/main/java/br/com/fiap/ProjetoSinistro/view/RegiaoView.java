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
@Table (name = "REGIAO")
public class RegiaoView extends RepresentationModel<RegiaoView> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id_regiao;

    private String nm_regiao;

    public RegiaoView(UUID id_regiao, String nm_regiao) {
        super();
        this.id_regiao = id_regiao;
        this.nm_regiao = nm_regiao;
    }

    public UUID getId_regiao() {
        return id_regiao;
    }

    public void setId_regiao(UUID id_regiao) {
        this.id_regiao = id_regiao;
    }

    public String getNm_regiao() {
        return nm_regiao;
    }

    public void setNm_regiao(String nm_regiao) {
        this.nm_regiao = nm_regiao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegiaoView that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id_regiao, that.id_regiao) && Objects.equals(nm_regiao, that.nm_regiao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_regiao, nm_regiao);
    }

    @Override
    public String toString() {
        return "RegiaoView{" +
                "id_regiao=" + id_regiao +
                ", nm_regiao='" + nm_regiao + '\'' +
                '}';
    }
}
