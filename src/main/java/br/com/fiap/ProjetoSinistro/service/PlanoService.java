package br.com.fiap.ProjetoSinistro.service;

import br.com.fiap.ProjetoSinistro.dto.PlanoDTO;
import br.com.fiap.ProjetoSinistro.repositorios.PlanoRepository;
import br.com.fiap.ProjetoSinistro.view.PlanoView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PlanoService {
    private final PlanoRepository planoRepository;

    public PlanoDTO salvar(PlanoDTO planoDTO) {
        PlanoView planoView = toEntity(planoDTO);
        if (planoDTO.getId_plano() == null) {
            planoView = planoRepository.save(planoView);
        } else {
            PlanoDTO byId = this.findById(planoDTO.getId_plano());
            byId.setTp_plano(planoView.getTp_plano());
            byId.setNr_carteirinha(planoView.getNr_carteirinha());
            planoView = planoRepository.save(toEntity(byId));
        }
        return toDto(planoView);
    }

    public List<PlanoDTO> findAll() {
        List<PlanoView> list = planoRepository.findAll();
        List<PlanoDTO> planoDTOList = list.stream().map(PlanoService::toDto).toList();
        return planoDTOList;
    }

    public void deletarPorId(UUID id) {
        planoRepository.deleteById(id);

    }

    public PlanoDTO findById(UUID id) {
        Optional<PlanoView> byId = planoRepository.findById(id);
        if (byId.isPresent()){
            return toDto(byId.get());
        }
        throw new RuntimeException("uuid not found");
    }


    private static PlanoView toEntity(PlanoDTO planoDTO) {
        PlanoView planoView = new PlanoView();
        planoView.setId_plano(planoDTO.getId_plano());
        planoView.setTp_plano(planoDTO.getTp_plano());
        planoView.setNr_carteirinha(planoDTO.getNr_carteirinha());
        return planoView;
    }

    private static PlanoDTO toDto(PlanoView planoView) {
        PlanoDTO planoDTO = new PlanoDTO();
        planoDTO.setId_plano(planoView.getId_plano());
        planoDTO.setTp_plano(planoView.getTp_plano());
        planoDTO.setNr_carteirinha(planoView.getNr_carteirinha());
        return planoDTO;
    }
}
