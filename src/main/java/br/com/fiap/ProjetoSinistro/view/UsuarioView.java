package br.com.fiap.ProjetoSinistro.view;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.ToString;
import java.util.UUID;

@ToString
@Entity
@Table(name = "USUARIO")
public class UsuarioView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_usuario;

    @NotBlank (message = "O e-mail é obrigatório")
    private String email;

    @NotBlank (message = "A senha é obrigatória")
    private String senha;

}
