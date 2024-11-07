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
@Table(name ="MUICIPIO")
public class MunicipioView extends RepresentationModel<MunicipioView> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id_municipio;

    private String nm_municipio;

    public MunicipioView(UUID id_municipio, String nm_municipio) {
        super();
        this.id_municipio = id_municipio;
        this.nm_municipio = nm_municipio;
    }

    public UUID getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(UUID id_municipio) {
        this.id_municipio = id_municipio;
    }

    public String getNm_municipio() {
        return nm_municipio;
    }

    public void setNm_municipio(String nm_municipio) {
        this.nm_municipio = nm_municipio;
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
