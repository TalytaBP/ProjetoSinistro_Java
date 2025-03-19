package br.com.fiap.ProjetoSinistro.view;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@ToString
@Entity
@Table(name = "CONSULTORIO")
@Data
public class ConsultorioView extends RepresentationModel<ConsultorioView> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_consultorio;

}
