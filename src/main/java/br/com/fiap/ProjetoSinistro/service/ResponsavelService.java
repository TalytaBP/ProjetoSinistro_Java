package br.com.fiap.ProjetoSinistro.service;

import br.com.fiap.ProjetoSinistro.dto.ResponsavelPacienteDTO;
import br.com.fiap.ProjetoSinistro.repositorios.ResponsavelPacienteRepository;
import br.com.fiap.ProjetoSinistro.view.ResponsavelPacienteView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ResponsavelService {

    private final ResponsavelPacienteRepository responsavelPacienteRepository;

    private ResponsavelPacienteDTO convertToDTO(ResponsavelPacienteView responsavelPacienteView) {
        ResponsavelPacienteDTO dto = new ResponsavelPacienteDTO();
        dto.setId_responsavel(responsavelPacienteView.getId_responsavel());
        dto.setNm_completo(responsavelPacienteView.getNm_completo());
        dto.setNr_cpf(responsavelPacienteView.getNr_cpf());
        dto.setNr_rg(responsavelPacienteView.getNr_rg());
        dto.setTp_parentesco(responsavelPacienteView.getTp_parentesco());

        return dto;
    }

    private ResponsavelPacienteView convertToEntity(ResponsavelPacienteDTO dto) {
        ResponsavelPacienteView responsavelPacienteView = new ResponsavelPacienteView();
        responsavelPacienteView.setId_responsavel(dto.getId_responsavel());
        responsavelPacienteView.setNm_completo(dto.getNm_completo());
        responsavelPacienteView.setNr_cpf(dto.getNr_cpf());
        responsavelPacienteView.setNr_rg(dto.getNr_rg());
        return responsavelPacienteView;
    }

    public ResponsavelPacienteDTO save(ResponsavelPacienteDTO responsavelPacienteDTO) {
        ResponsavelPacienteView responsavelPacienteView = convertToEntity(responsavelPacienteDTO);
        responsavelPacienteView = responsavelPacienteRepository.save(responsavelPacienteView);
        return convertToDTO(responsavelPacienteView);
    }

    public List<ResponsavelPacienteDTO> getResponsavelPacienteViews() {
        List<ResponsavelPacienteDTO> responsavelPacienteViews = responsavelPacienteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return responsavelPacienteViews;
    }

    public void deletarPorId(UUID id) {
        responsavelPacienteRepository.deleteById(id);

    }

    public ResponsavelPacienteDTO findById(UUID id) {
        Optional<ResponsavelPacienteView> byId = responsavelPacienteRepository.findById(id);
        if (byId.isPresent()){
            return convertToDTO(byId.get());
        }
        throw new RuntimeException("id not found");
    }

}

