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
@Table(name = "USUARIO")
public class UsuarioView extends RepresentationModel <UsuarioView> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id_usuario;

    private String email;

    private String senha;

    public UsuarioView(UUID id_usuario, String email, String senha) {
        super();
        this.id_usuario = id_usuario;
        this.email = email;
        this.senha = senha;
    }

    public UUID getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(UUID id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioView that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id_usuario, that.id_usuario) && Objects.equals(email, that.email) && Objects.equals(senha, that.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_usuario, email, senha);
    }

    @Override
    public String toString() {
        return "UsuarioView{" +
                "id_usuario=" + id_usuario +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

}
