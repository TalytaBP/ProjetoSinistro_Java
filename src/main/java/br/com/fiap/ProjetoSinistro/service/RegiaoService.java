package br.com.fiap.ProjetoSinistro.service;

import br.com.fiap.ProjetoSinistro.dto.RegiaoDTO;
import br.com.fiap.ProjetoSinistro.repositorios.RegiaoRepository;
import br.com.fiap.ProjetoSinistro.view.RegiaoView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RegiaoService {

    private final RegiaoRepository regiaoRepository;

    public RegiaoDTO salvar(RegiaoDTO regiaoDTO) {
        RegiaoView regiaoView = toEntity(regiaoDTO);

        if (regiaoDTO.getId_regiao() == null) {
            regiaoView = regiaoRepository.save(regiaoView);
        } else {
            RegiaoDTO byId = this.findById(regiaoDTO.getId_regiao());
            byId.setNm_regiao(regiaoView.getNm_regiao());
            regiaoView = regiaoRepository.save(toEntity(byId));
        }
        return toDto(regiaoView);
    }

    public List<RegiaoDTO> findAll() {
        List<RegiaoView> list = regiaoRepository.findAll();
        List<RegiaoDTO> regiaoDTOList = list.stream().map(RegiaoService::toDto).toList();
        return regiaoDTOList;
    }

    public void deletarPorId(UUID id) {
        regiaoRepository.deleteById(id);

    }

    public RegiaoDTO findById(UUID id) {
        Optional<RegiaoView> byId = regiaoRepository.findById(id);
        if (byId.isPresent()){
            return toDto(byId.get());
        }
        throw new RuntimeException("id not found");
    }


    private static RegiaoView toEntity(RegiaoDTO regiaoDTO) {
        RegiaoView regiaoView = new RegiaoView();
        regiaoView.setId_regiao(regiaoDTO.getId_regiao());
        regiaoView.setNm_regiao(regiaoDTO.getNm_regiao());
        return regiaoView;
    }

    private static RegiaoDTO toDto(RegiaoView regiaoView) {
        RegiaoDTO regiaoDTO = new RegiaoDTO();
        regiaoDTO.setId_regiao(regiaoView.getId_regiao());
        regiaoDTO.setNm_regiao(regiaoView.getNm_regiao());
        return regiaoDTO;
    }
}
