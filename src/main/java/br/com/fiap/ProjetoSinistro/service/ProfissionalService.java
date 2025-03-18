package br.com.fiap.ProjetoSinistro.service;

import br.com.fiap.ProjetoSinistro.dto.ProfissionalDTO;
import br.com.fiap.ProjetoSinistro.repositorios.ProfissionalRepository;
import br.com.fiap.ProjetoSinistro.view.ProfissionalView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;

    private ProfissionalDTO convertToDTO(ProfissionalView profissionalView) {
        ProfissionalDTO dto = new ProfissionalDTO();
        dto.setId_profissional(profissionalView.getId_profissional());
        dto.setEspecialista(profissionalView.getEspecialista());
        dto.setNm_completo(profissionalView.getNm_completo());
        return dto;
    }

    private ProfissionalView convertToEntity(ProfissionalDTO dto) {
        ProfissionalView profissionalView = new ProfissionalView();
        profissionalView.setId_profissional(dto.getId_profissional());
        profissionalView.setEspecialista(dto.getEspecialista());
        profissionalView.setNm_completo(dto.getNm_completo());
        return profissionalView;
    }

    public ProfissionalDTO save(ProfissionalDTO profissionalDTO) {
        ProfissionalView profissionalView = convertToEntity(profissionalDTO);
        profissionalView = profissionalRepository.save(profissionalView);
        return convertToDTO(profissionalView);
    }

    public List<ProfissionalDTO> getProfissionalViews() {
        List<ProfissionalDTO>  profissionalViews = profissionalRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return  profissionalViews;
    }

    public void deletarPorId(UUID id) {
        profissionalRepository.deleteById(id);

    }

    public ProfissionalDTO findById(UUID id) {
        Optional<ProfissionalView> byId = profissionalRepository.findById(id);
        if (byId.isPresent()){
            return convertToDTO(byId.get());
        }
        throw new RuntimeException("id not found");
    }

}
