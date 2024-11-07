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
@Table(name = "PLANO")
public class PlanoView extends RepresentationModel<PlanoView> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id_plano;

    private String tp_plano;

    private int nr_carteirinha;

    public PlanoView(UUID id_plano, String tp_plano, int nr_carteirinha) {
        super();
        this.id_plano = id_plano;
        this.tp_plano = tp_plano;
        this.nr_carteirinha = nr_carteirinha;
    }

    public UUID getId_plano() {
        return id_plano;
    }

    public void setId_plano(UUID id_plano) {
        this.id_plano = id_plano;
    }

    public String getTp_plano() {
        return tp_plano;
    }

    public void setTp_plano(String tp_plano) {
        this.tp_plano = tp_plano;
    }

    public int getNr_carteirinha() {
        return nr_carteirinha;
    }

    public void setNr_carteirinha(int nr_carteirinha) {
        this.nr_carteirinha = nr_carteirinha;
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
