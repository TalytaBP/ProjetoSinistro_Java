package br.com.fiap.ProjetoSinistro.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SintomasRepositorio extends JpaRepository<SintomasRepositorio, UUID> {
    void add(Link listaDeSintomas);

    UUID getId_sintoma();
}
