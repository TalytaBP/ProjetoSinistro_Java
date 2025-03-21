package br.com.fiap.ProjetoSinistro.repositorios;

import br.com.fiap.ProjetoSinistro.view.AnaliseSaudeBucalView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnaliseSaudeBucalRepository extends JpaRepository <AnaliseSaudeBucalView, UUID> {
}
