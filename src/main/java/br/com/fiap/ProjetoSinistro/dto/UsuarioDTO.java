package br.com.fiap.ProjetoSinistro.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private UUID id_usuario;

    @NotBlank(message = "O e-mail é obrigatório")
    private String email;

    @NotBlank (message = "A senha é obrigatória")
    private String senha;

}
