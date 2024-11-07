package br.com.fiap.ProjetoSinistro.view;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
//@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "PACIENTE")
public class PacienteView extends RepresentationModel<PacienteView> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id_paciente;

    private String nm_completo;

    private int nr_idade;

    private String tp_sexo;

    private int nr_cpf;

    private int nr_rg;

    public PacienteView(UUID id_paciente, String nm_completo, int nr_idade, String tp_sexo, int nr_cpf, int nr_rg) {
        super();
        this.id_paciente = id_paciente;
        this.nm_completo = nm_completo;
        this.nr_idade = nr_idade;
        this.tp_sexo = tp_sexo;
        this.nr_cpf = nr_cpf;
        this.nr_rg = nr_rg;
    }

    public UUID getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(UUID id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getNm_completo() {
        return nm_completo;
    }

    public void setNm_completo(String nm_completo) {
        this.nm_completo = nm_completo;
    }

    public int getNr_idade() {
        return nr_idade;
    }

    public void setNr_idade(int nr_idade) {
        this.nr_idade = nr_idade;
    }

    public String getTp_sexo() {
        return tp_sexo;
    }

    public void setTp_sexo(String tp_sexo) {
        this.tp_sexo = tp_sexo;
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

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
