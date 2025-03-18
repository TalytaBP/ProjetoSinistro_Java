package br.com.fiap.ProjetoSinistro.repositorios;

import br.com.fiap.ProjetoSinistro.view.ConsultorioView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConsultorioRepository extends JpaRepository <ConsultorioView, UUID> {
}
