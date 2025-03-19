package br.com.fiap.ProjetoSinistro.service;

import br.com.fiap.ProjetoSinistro.dto.PacienteDTO;
import br.com.fiap.ProjetoSinistro.repositorios.PacienteRepository;
import br.com.fiap.ProjetoSinistro.view.PacienteView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public PacienteDTO salvar(PacienteDTO pacienteDTO) {
        PacienteView pacienteView = toEntity(pacienteDTO);
        if (pacienteDTO.getId_paciente() == null) {
            pacienteView = pacienteRepository.save(pacienteView);
        } else {
            PacienteDTO byId = this.findById(pacienteDTO.getId_paciente());
            byId.setNm_completo(pacienteView.getNm_completo());
            byId.setNr_idade(pacienteView.getNr_idade());
            byId.setTp_sexo(pacienteView.getTp_sexo());
            byId.setNr_cpf(pacienteView.getNr_cpf());
            byId.setNr_rg(pacienteView.getNr_rg());
            pacienteView = pacienteRepository.save(toEntity(byId));
        }
        return toDto(pacienteView);
    }

    public List<PacienteDTO> findAll() {
        List<PacienteView> list = pacienteRepository.findAll();
        List<PacienteDTO> pacienteDTOList = list.stream().map(PacienteService::toDto).toList();
        return pacienteDTOList;
    }

    public void deletarPorId(UUID id) {
        pacienteRepository.deleteById(id);

    }

    public PacienteDTO findById(UUID id) {
        Optional<PacienteView> byId = pacienteRepository.findById(id);
        if (byId.isPresent()){
            return toDto(byId.get());
        }
        throw new RuntimeException("id not found");
    }


    private static PacienteView toEntity(PacienteDTO pacienteDTO) {
        PacienteView pacienteView = new PacienteView();
        pacienteView.setId_paciente(pacienteDTO.getId_paciente());
        pacienteView.setNm_completo(pacienteDTO.getNm_completo());
        pacienteView.setNr_idade(pacienteDTO.getNr_idade());
        pacienteView.setTp_sexo(pacienteDTO.getTp_sexo());
        pacienteView.setNr_cpf(pacienteDTO.getNr_cpf());
        pacienteView.setNr_rg(pacienteDTO.getNr_rg());
        return pacienteView;
    }

    private static PacienteDTO toDto(PacienteView pacienteView) {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId_paciente(pacienteView.getId_paciente());
        pacienteDTO.setNm_completo(pacienteView.getNm_completo());
        pacienteDTO.setNr_idade(pacienteView.getNr_idade());
        pacienteDTO.setTp_sexo(pacienteView.getTp_sexo());
        pacienteDTO.setNr_cpf(pacienteView.getNr_cpf());
        pacienteDTO.setNr_rg(pacienteView.getNr_rg());
        return pacienteDTO;
    }

}
