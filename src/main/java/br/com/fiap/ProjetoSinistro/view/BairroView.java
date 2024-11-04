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
@Table(name = "BAIRRO")

public class BairroView extends RepresentationModel<BairroView> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id_bairro;

    private String nm_bairro;

    public BairroView(UUID id_bairro, String nm_bairro) {
        super();
        this.id_bairro = id_bairro;
        this.nm_bairro = nm_bairro;
    }

    public UUID getId_bairro() {
        return id_bairro;
    }

    public void setId_bairro(UUID id_bairro) {
        this.id_bairro = id_bairro;
    }

    public String getNm_bairro() {
        return nm_bairro;
    }

    public void setNm_bairro(String nm_bairro) {
        this.nm_bairro = nm_bairro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BairroView that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id_bairro, that.id_bairro) && Objects.equals(nm_bairro, that.nm_bairro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_bairro, nm_bairro);
    }

    @Override
    public String toString() {
        return "BairroView{" +
                "id_bairro=" + id_bairro +
                ", nm_bairro='" + nm_bairro + '\'' +
                '}';
    }
}
