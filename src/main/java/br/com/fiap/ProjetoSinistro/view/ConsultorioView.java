package br.com.fiap.ProjetoSinistro.view;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@ToString
@Entity
@Table(name = "CONSULTORIO")

public class ConsultorioView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_consultorio;

}
