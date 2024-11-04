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
}
