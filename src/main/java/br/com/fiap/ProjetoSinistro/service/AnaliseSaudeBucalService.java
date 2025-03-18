package br.com.fiap.ProjetoSinistro.service;

import br.com.fiap.ProjetoSinistro.dto.AnaliseSaudeBucalDTO;
import br.com.fiap.ProjetoSinistro.repositorios.AnaliseSaudeBucalRepository;
import br.com.fiap.ProjetoSinistro.view.AnaliseSaudeBucalView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AnaliseSaudeBucalService {

    private final AnaliseSaudeBucalRepository analiseSaudeBucalRepository;

    public AnaliseSaudeBucalDTO salvar(AnaliseSaudeBucalDTO analiseSaudeBucalDTO) {
        AnaliseSaudeBucalView analiseSaudeBucalView = toEntity(analiseSaudeBucalDTO);
        if (analiseSaudeBucalDTO.getId_scanner() == null) {
            analiseSaudeBucalView = analiseSaudeBucalRepository.save(analiseSaudeBucalView);
        } else {
            AnaliseSaudeBucalDTO byId = this.findById(analiseSaudeBucalDTO.getId_scanner());
            byId.setQualidade_gengiva(analiseSaudeBucalView.getQualidade_gengiva());
            byId.setQualidade_dente(analiseSaudeBucalView.getQualidade_dente());
            byId.setColoracao_gengiva(analiseSaudeBucalView.getColoracao_gengiva());
            byId.setColoracao_dente(analiseSaudeBucalView.getColoracao_dente());
            byId.setSangramento(analiseSaudeBucalView.getSangramento());
            byId.setInfeccao(analiseSaudeBucalView.getInfeccao());
            analiseSaudeBucalView = analiseSaudeBucalRepository.save(toEntity(byId));
        }
        return toDto(analiseSaudeBucalView);
    }

    public List<AnaliseSaudeBucalDTO> findAll() {
        List<AnaliseSaudeBucalView> list = analiseSaudeBucalRepository.findAll();
        List<AnaliseSaudeBucalDTO> analiseSaudeBucalDTOList = list.stream().map(AnaliseSaudeBucalService::toDto).toList();
        return analiseSaudeBucalDTOList;
    }

    public void deletarPorId(UUID id) {
        analiseSaudeBucalRepository.deleteById(id);

    }

    public AnaliseSaudeBucalDTO findById(UUID id) {
        Optional<AnaliseSaudeBucalView> byId = analiseSaudeBucalRepository.findById(id);
        if (byId.isPresent()){
            return toDto(byId.get());
        }
        throw new RuntimeException("uuid not found");
    }


    private static AnaliseSaudeBucalView toEntity(AnaliseSaudeBucalDTO analiseSaudeBucalDTO) {
        AnaliseSaudeBucalView analiseSaudeBucalView = new AnaliseSaudeBucalView();
        analiseSaudeBucalView.setId_scanner(analiseSaudeBucalDTO.getId_scanner());
        analiseSaudeBucalView.setQualidade_gengiva(analiseSaudeBucalDTO.geQualidade_gengiva());
        analiseSaudeBucalView.setQualidade_dente(analiseSaudeBucalDTO.getQualidade_dente());
        analiseSaudeBucalView.setColoracao_gengiva(analiseSaudeBucalDTO.getColoracao_gengiva());
        analiseSaudeBucalView.setColoracao_dente(analiseSaudeBucalDTO.getColoracao_dente());
        analiseSaudeBucalView.setSangramento(analiseSaudeBucalDTO.getSangramento());
        analiseSaudeBucalView.setInfeccao(analiseSaudeBucalDTO.getInfeccao());
        analiseSaudeBucalView.setPaciente(analiseSaudeBucalDTO.getPaciente());
        analiseSaudeBucalView.setResultado(analiseSaudeBucalDTO.getResultado());
        analiseSaudeBucalView.setSintomas(analiseSaudeBucalDTO.getSintomas());
        analiseSaudeBucalView.setConsultorio(analiseSaudeBucalDTO.getConsultorio());
        return analiseSaudeBucalView;
    }

    private static AnaliseSaudeBucalDTO toDto(AnaliseSaudeBucalView analiseSaudeBucalView) {
        AnaliseSaudeBucalDTO analiseSaudeBucalDTO = new AnaliseSaudeBucalDTO();
        analiseSaudeBucalDTO.setId_scanner(analiseSaudeBucalView.getId_scanner());
        analiseSaudeBucalDTO.setQualidade_gengiva(analiseSaudeBucalView.getQualidade_gengiva());
        analiseSaudeBucalDTO.setQualidade_dente(analiseSaudeBucalView.getQualidade_dente());
        analiseSaudeBucalDTO.setColoracao_gengiva(analiseSaudeBucalView.getColoracao_gengiva());
        analiseSaudeBucalDTO.setColoracao_dente(analiseSaudeBucalView.getColoracao_dente());
        analiseSaudeBucalDTO.setSangramento(analiseSaudeBucalView.getSangramento());
        analiseSaudeBucalDTO.setPaciente(analiseSaudeBucalView.getPaciente());
        analiseSaudeBucalDTO.setResultado(analiseSaudeBucalView.getResultado());
        analiseSaudeBucalDTO.setSintomas(analiseSaudeBucalView.getSintomas());
        analiseSaudeBucalDTO.setConsultorio(analiseSaudeBucalView.getConsultorio());
        analiseSaudeBucalDTO.setInfeccao(analiseSaudeBucalView.getInfeccao());
        return analiseSaudeBucalDTO;
    }
}
