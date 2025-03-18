package br.com.fiap.ProjetoSinistro.service;

import br.com.fiap.ProjetoSinistro.dto.TipoPlanoDTO;
import br.com.fiap.ProjetoSinistro.repositorios.TipoPlanoRepository;
import br.com.fiap.ProjetoSinistro.view.TelefoneView;
import br.com.fiap.ProjetoSinistro.view.TipoPlanoView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TipoPlanoService {

    private final TipoPlanoRepository repository;

    private TipoPlanoDTO convertToDTO(TipoPlanoView tipoPlanoView) {
        TipoPlanoDTO dto = new TipoPlanoDTO();
        dto.setId_tp_plano(tipoPlanoView.getId_tp_plano());
        dto.setNm_plano(tipoPlanoView.getNm_plano());
        return dto;
    }

    private TipoPlanoView convertToEntity(TipoPlanoDTO dto) {
        TipoPlanoView tipoPlanoView = new TipoPlanoView();
        tipoPlanoView.setId(dto.getId());
        tipoPlanoView.setNome(dto.getNome());
        return tipoPlanoView;
    }

    public TipoPlanoDTO save(TipoPlanoDTO tipoPlanoDTO) {
        TipoPlanoView tipoPlanoView = convertToEntity(tipoPlanoDTO);
        tipoPlanoView = repository.save(tipoPlanoView);
        return convertToDTO(tipoPlanoView);
    }

    public List<TipoPlanoDTO> gettipoPlanoViews() {
        List<TipoPlanoDTO> tipoPlanoViews = repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return tipoPlanoViews;
    }

    public void deletarPorId(UUID id) {
        repository.deleteById(id);

    }

    public TipoPlanoDTO findById(UUID id) {
        Optional<TipoPlanoView> byId = repository.findById(id);
        if (byId.isPresent()){
            return convertToDTO(byId.get());
        }
        throw new RuntimeException("id not found");
    }

}
