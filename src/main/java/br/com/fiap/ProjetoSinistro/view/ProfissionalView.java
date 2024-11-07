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
@Table (name = "PROFISSIONAL")
public class ProfissionalView extends RepresentationModel <ProfissionalView> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id_profissional;

    private String especialista;

    private String nm_completo;

    public ProfissionalView(UUID id_profissional, String especialista, String nm_completo) {
        super();
        this.id_profissional = id_profissional;
        this.especialista = especialista;
        this.nm_completo = nm_completo;
    }

    public UUID getId_profissional() {
        return id_profissional;
    }

    public void setId_profissional(UUID id_profissional) {
        this.id_profissional = id_profissional;
    }

    public String getEspecialista() {
        return especialista;
    }

    public void setEspecialista(String especialista) {
        this.especialista = especialista;
    }

    public String getNm_completo() {
        return nm_completo;
    }

    public void setNm_completo(String nm_completo) {
        this.nm_completo = nm_completo;
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
