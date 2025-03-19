package br.com.fiap.ProjetoSinistro.view;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@ToString
@Entity
@Table(name = "USUARIO")
@Data
public class UsuarioView extends RepresentationModel<UsuarioView> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_usuario;

    @NotBlank (message = "O e-mail é obrigatório")
    private String email;

    @NotBlank (message = "A senha é obrigatória")
    private String senha;

}
