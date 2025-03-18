package br.com.fiap.ProjetoSinistro.service;

import br.com.fiap.ProjetoSinistro.dto.ConsultorioDTO;
import br.com.fiap.ProjetoSinistro.repositorios.ConsultorioRepository;
import br.com.fiap.ProjetoSinistro.view.ConsultorioView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConsultorioService {

    private final ConsultorioRepository consultorioRepository;

    public ConsultorioDTO salvar(ConsultorioDTO consultorioDTO) {
        ConsultorioView consultorioView = toEntity(consultorioDTO);
        if (consultorioDTO.getId_consultorio() == null) {
            consultorioView = consultorioRepository.save(consultorioView);
        } else {
            ConsultorioDTO byId = this.findById(consultorioDTO.getId_consultorio());
            consultorioView = consultorioRepository.save(toEntity(byId));
        }
        return toDto(consultorioView);
    }

    public List<ConsultorioDTO> findAll() {
        List<ConsultorioView> list = consultorioRepository.findAll();
        List<ConsultorioDTO> consultorioDTOList = list.stream().map(ConsultorioService::toDto).toList();
        return consultorioDTOList;
    }

    public void deletarPorId(UUID id) {
        consultorioRepository.deleteById(id);

    }

    public ConsultorioDTO findById(UUID id) {
        Optional<ConsultorioView> byId = consultorioRepository.findById(id);
        if (byId.isPresent()){
            return toDto(byId.get());
        }
        throw new RuntimeException("id not found");
    }


    private static ConsultorioView toEntity(ConsultorioDTO consultorioDTO) {
        ConsultorioView consultorioView = new ConsultorioView();
        consultorioView.setId_consultorio(consultorioDTO.getId_consultorio());
        consultorioView.setProfissional(consultorioDTO.getProfissional());
        return consultorioView;
    }

    private static ConsultorioDTO toDto(ConsultorioView consultorioView) {
        ConsultorioDTO consultorioDTO = new ConsultorioDTO();
        consultorioDTO.setId_consultorio(consultorioView.getId_consultorio());
        consultorioDTO.setProfissional(consultorioView.getProfissional());
        return consultorioDTO;
    }
}
