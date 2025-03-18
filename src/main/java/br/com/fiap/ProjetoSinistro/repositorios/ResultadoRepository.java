package br.com.fiap.ProjetoSinistro.repositorios;

import br.com.fiap.ProjetoSinistro.view.ResultadoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResultadoRepository extends JpaRepository<ResultadoView, UUID> {
}
