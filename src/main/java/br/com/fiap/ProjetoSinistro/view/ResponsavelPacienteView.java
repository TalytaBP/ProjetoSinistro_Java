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
@Table (name ="RESPONSAVEL_PACIENTE")
public class ResponsavelPacienteView extends RepresentationModel <ResponsavelPacienteView> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id_responsavel;

    private String nm_completo;

    private int nr_cpf;

    private int nr_rg;

    private String tp_parentesco;

    public ResponsavelPacienteView(UUID id_responsavel, String nm_completo, int nr_cpf, int nr_rg, String tp_parentesco) {
        super();
        this.id_responsavel = id_responsavel;
        this.nm_completo = nm_completo;
        this.nr_cpf = nr_cpf;
        this.nr_rg = nr_rg;
        this.tp_parentesco = tp_parentesco;
    }

    public UUID getId_responsavel() {
        return id_responsavel;
    }

    public void setId_responsavel(UUID id_responsavel) {
        this.id_responsavel = id_responsavel;
    }

    public String getNm_completo() {
        return nm_completo;
    }

    public void setNm_completo(String nm_completo) {
        this.nm_completo = nm_completo;
    }

    public int getNr_cpf() {
        return nr_cpf;
    }

    public void setNr_cpf(int nr_cpf) {
        this.nr_cpf = nr_cpf;
    }

    public int getNr_rg() {
        return nr_rg;
    }

    public void setNr_rg(int nr_rg) {
        this.nr_rg = nr_rg;
    }

    public String getTp_parentesco() {
        return tp_parentesco;
    }

    public void setTp_parentesco(String tp_parentesco) {
        this.tp_parentesco = tp_parentesco;
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
