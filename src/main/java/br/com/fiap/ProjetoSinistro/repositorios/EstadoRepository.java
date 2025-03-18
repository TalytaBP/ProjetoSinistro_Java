package br.com.fiap.ProjetoSinistro.repositorios;

import br.com.fiap.ProjetoSinistro.view.EstadoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoView, UUID> {
}
