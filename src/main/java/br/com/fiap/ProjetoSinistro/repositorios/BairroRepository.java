package br.com.fiap.ProjetoSinistro.repositorios;

import br.com.fiap.ProjetoSinistro.view.BairroView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BairroRepository extends JpaRepository <BairroView, UUID> {
}
