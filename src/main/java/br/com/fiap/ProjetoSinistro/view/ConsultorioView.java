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
@Table(name = "CONSULTORIO")

public class ConsultorioView extends RepresentationModel<ConsultorioView> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id_consultorio;

    public ConsultorioView(UUID id_consultorio) {
        super();
        this.id_consultorio = id_consultorio;
    }

    public UUID getId_consultorio() {
        return id_consultorio;
    }

    public void setId_consultorio(UUID id_consultorio) {
        this.id_consultorio = id_consultorio;
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
