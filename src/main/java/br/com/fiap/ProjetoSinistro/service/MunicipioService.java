package br.com.fiap.ProjetoSinistro.service;

import br.com.fiap.ProjetoSinistro.dto.MunicipioDTO;
import br.com.fiap.ProjetoSinistro.repositorios.MunicipioRepository;
import br.com.fiap.ProjetoSinistro.view.MunicipioView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MunicipioService {

    private final MunicipioRepository municipioRepository;

    public MunicipioDTO salvar(MunicipioDTO municipioDTO) {
        MunicipioView municipioView = toEntity(municipioDTO);
        if (municipioDTO.getId_municipio() == null) {
            municipioView = municipioRepository.save(municipioView);
        } else {
            MunicipioDTO byId = this.findById(municipioDTO.getId_municipio());
            byId.setNm_municipio(MunicipioView.getNm_municipio());
            municipioView = municipioRepository.save(toEntity(byId));
        }
        return toDto(municipioView);
    }

    public List<MunicipioDTO> findAll() {
        List<MunicipioView> list = municipioRepository.findAll();
        List<MunicipioDTO> municipioDTOList = list.stream().map(MunicipioService::toDto).toList();
        return municipioDTOList;
    }

    public void deletarPorId(UUID id) {
        municipioRepository.deleteById(id);

    }

    public MunicipioDTO findById(UUID id) {
        Optional<MunicipioView> byId = municipioRepository.findById(id);
        if (byId.isPresent()){
            return toDto(byId.get());
        }
        throw new RuntimeException("id not found");
    }


    private static MunicipioView toEntity(MunicipioDTO municipioDTO) {
        MunicipioView municipioView = new MunicipioView();
        municipioView.setId_municipio(municipioDTO.getId_municipio());
        municipioView.setNm_municipio(municipioDTO.getNm_municipio());
        municipioView.setEstado(municipioDTO.getEstado());
        return municipioView;
    }

    private static MunicipioDTO toDto(MunicipioView municipioView) {
        MunicipioDTO municipioDTO = new MunicipioDTO();
        municipioDTO.setId_municipio(municipioView.getId_municipio());
        municipioDTO.setNome(municipioView.getNome());
        municipioDTO.setEstado(municipioView.getEstado());
        return municipioDTO;
    }
}
