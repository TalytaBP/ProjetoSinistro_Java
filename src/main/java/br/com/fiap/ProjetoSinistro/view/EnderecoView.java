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
@Table(name = "ENDERECO")
public class EnderecoView extends RepresentationModel<EnderecoView> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id_endereco;

    private int nr_cep;

    private int nr_complemento;

    private String nm_logradouro;

    private String nm_rua;

    public EnderecoView(UUID id_endereco, int nr_cep, int nr_complemento, String nm_logradouro, String nm_rua) {
        super();
        this.id_endereco = id_endereco;
        this.nr_cep = nr_cep;
        this.nr_complemento = nr_complemento;
        this.nm_logradouro = nm_logradouro;
        this.nm_rua = nm_rua;
    }

    public UUID getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(UUID id_endereco) {
        this.id_endereco = id_endereco;
    }

    public int getNr_cep() {
        return nr_cep;
    }

    public void setNr_cep(int nr_cep) {
        this.nr_cep = nr_cep;
    }

    public int getNr_complemento() {
        return nr_complemento;
    }

    public void setNr_complemento(int nr_complemento) {
        this.nr_complemento = nr_complemento;
    }

    public String getNm_logradouro() {
        return nm_logradouro;
    }

    public void setNm_logradouro(String nm_logradouro) {
        this.nm_logradouro = nm_logradouro;
    }

    public String getNm_rua() {
        return nm_rua;
    }

    public void setNm_rua(String nm_rua) {
        this.nm_rua = nm_rua;
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
