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
@Table(name = "ANALISE_SAUDE_BUCAL")
public class AnaliseSaudeBucalView extends RepresentationModel<AnaliseSaudeBucalView> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id_scanner;

    private String qualidade_gengiva;

    private String qualidade_dente;

    private String coloracao_gengiva;

    private String coloracao_dente;

    private String sangramento;

    private String infeccao;

    public AnaliseSaudeBucalView(UUID id_scanner, String qualidade_gengiva, String qualidade_dente, String coloracao_gengiva, String coloracao_dente, String sangramento, String infeccao) {
        super();
        this.id_scanner = id_scanner;
        this.qualidade_gengiva = qualidade_gengiva;
        this.qualidade_dente = qualidade_dente;
        this.coloracao_gengiva = coloracao_gengiva;
        this.coloracao_dente = coloracao_dente;
        this.sangramento = sangramento;
        this.infeccao = infeccao;
    }

    public UUID getId_scanner() {
        return id_scanner;
    }

    public void setId_scanner(UUID id_scanner) {
        this.id_scanner = id_scanner;
    }

    public String getQualidade_gengiva() {
        return qualidade_gengiva;
    }

    public void setQualidade_gengiva(String qualidade_gengiva) {
        this.qualidade_gengiva = qualidade_gengiva;
    }

    public String getQualidade_dente() {
        return qualidade_dente;
    }

    public void setQualidade_dente(String qualidade_dente) {
        this.qualidade_dente = qualidade_dente;
    }

    public String getColoracao_gengiva() {
        return coloracao_gengiva;
    }

    public void setColoracao_gengiva(String coloracao_gengiva) {
        this.coloracao_gengiva = coloracao_gengiva;
    }

    public String getColoracao_dente() {
        return coloracao_dente;
    }

    public void setColoracao_dente(String coloracao_dente) {
        this.coloracao_dente = coloracao_dente;
    }

    public String getSangramento() {
        return sangramento;
    }

    public void setSangramento(String sangramento) {
        this.sangramento = sangramento;
    }

    public String getInfeccao() {
        return infeccao;
    }

    public void setInfeccao(String infeccao) {
        this.infeccao = infeccao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnaliseSaudeBucalView that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id_scanner, that.id_scanner) && Objects.equals(qualidade_gengiva, that.qualidade_gengiva) && Objects.equals(qualidade_dente, that.qualidade_dente) && Objects.equals(coloracao_gengiva, that.coloracao_gengiva) && Objects.equals(coloracao_dente, that.coloracao_dente) && Objects.equals(sangramento, that.sangramento) && Objects.equals(infeccao, that.infeccao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_scanner, qualidade_gengiva, qualidade_dente, coloracao_gengiva, coloracao_dente, sangramento, infeccao);
    }

    @Override
    public String toString() {
        return "AnaliseSaudeBucalView{" +
                "id_scanner=" + id_scanner +
                ", qualidade_gengiva='" + qualidade_gengiva + '\'' +
                ", qualidade_dente='" + qualidade_dente + '\'' +
                ", coloracao_gengiva='" + coloracao_gengiva + '\'' +
                ", coloracao_dente='" + coloracao_dente + '\'' +
                ", sangramento='" + sangramento + '\'' +
                ", infeccao='" + infeccao + '\'' +
                '}';
    }
}
