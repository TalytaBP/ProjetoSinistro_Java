package br.com.fiap.ProjetoSinistro.service;

import br.com.fiap.ProjetoSinistro.dto.SintomasDTO;
import br.com.fiap.ProjetoSinistro.repositorios.SintomasRepository;
import br.com.fiap.ProjetoSinistro.view.SintomasView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SintomasService {
    private final SintomasRepository sintomasRepository;

    private SintomasDTO convertToDTO(SintomasView sintomasView) {
        SintomasDTO dto = new SintomasDTO();
        dto.setId_sintoma(sintomasView.getId_sintoma());
        dto.setTp_sintoma(sintomasView.getTp_sintoma());
        return dto;
    }

    private SintomasView convertToEntity(SintomasDTO dto) {
        SintomasView sintomasView = new SintomasView();
        sintomasView.setId_sintoma(dto.getId_sintoma());
        sintomasView.setTp_sintoma(dto.getTp_sintoma());
        return sintomasView;
    }

    public SintomasDTO save(SintomasDTO sintomasDTO) {
        SintomasView sintomasView = convertToEntity(sintomasDTO);
        sintomasView = sintomasRepository.save(sintomasView);
        return convertToDTO(sintomasView);
    }

    public List<SintomasDTO> getSintomasViews() {
        List<SintomasDTO> sintomasViews = sintomasRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return sintomasViews;
    }

    public void deletarPorId(UUID id) {
        sintomasRepository.deleteById(id);

    }

    public SintomasDTO findById(UUID id) {
        Optional<SintomasView> byId = sintomasRepository.findById(id);
        if (byId.isPresent()){
            return convertToDTO(byId.get());
        }
        throw new RuntimeException("id not found");
    }

}
