package br.com.fiap.ProjetoSinistro.service;

import br.com.fiap.ProjetoSinistro.dto.EstadoDTO;
import br.com.fiap.ProjetoSinistro.repositorios.EstadoRepository;
import br.com.fiap.ProjetoSinistro.view.EstadoView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EstadoService {

    private final EstadoRepository estadoRepository;

    private EstadoDTO convertToDTO(EstadoView estadoView) {
        EstadoDTO dto = new EstadoDTO();
        dto.setId_estado(estadoView.getId_estado());
        dto.setNm_estado(estadoView.getNm_estado());
        return dto;
    }

    private EstadoView convertToEntity(EstadoDTO dto) {
        EstadoView estadoView = new EstadoView();
        estadoView.setId_estado(dto.getId_estado());
        estadoView.setNm_estado(dto.getNm_estado());
        return estadoView;
    }

    public EstadoDTO save(EstadoDTO estadoDTO) {
        EstadoView estadoView = convertToEntity(estadoDTO);
        estadoView = estadoRepository.save(estadoView);
        return convertToDTO(estadoView);
    }

    public List<EstadoDTO> getEstadoViews() {
        List<EstadoDTO> estadoViews = estadoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return estadoViews;
    }

    public void deletarPorId(UUID id) {
        estadoRepository.deleteById(id);

    }

    public EstadoDTO findById(UUID id) {
        Optional<EstadoView> byId = estadoRepository.findById(id);
        if (byId.isPresent()){
            return convertToDTO(byId.get());
        }
        throw new RuntimeException("id not found");
    }

}
