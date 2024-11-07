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
@Table (name = "TELEFONE")
public class TelefoneView extends RepresentationModel <TelefoneView> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)

    private UUID id_telefone;

    private String tp_contato;

    private int nr_telefone;

    private int nr_ddd;

    public TelefoneView(UUID id_telefone, String tp_contato, int nr_telefone, int nr_ddd) {
        super();
        this.id_telefone = id_telefone;
        this.tp_contato = tp_contato;
        this.nr_telefone = nr_telefone;
        this.nr_ddd = nr_ddd;
    }

    public UUID getId_telefone() {
        return id_telefone;
    }

    public void setId_telefone(UUID id_telefone) {
        this.id_telefone = id_telefone;
    }

    public String getTp_contato() {
        return tp_contato;
    }

    public void setTp_contato(String tp_contato) {
        this.tp_contato = tp_contato;
    }

    public int getNr_telefone() {
        return nr_telefone;
    }

    public void setNr_telefone(int nr_telefone) {
        this.nr_telefone = nr_telefone;
    }

    public int getNr_ddd() {
        return nr_ddd;
    }

    public void setNr_ddd(int nr_ddd) {
        this.nr_ddd = nr_ddd;
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
