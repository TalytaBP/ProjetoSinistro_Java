package br.com.fiap.ProjetoSinistro.service;

import br.com.fiap.ProjetoSinistro.dto.ResultadoDTO;
import br.com.fiap.ProjetoSinistro.repositorios.ResultadoRepository;
import br.com.fiap.ProjetoSinistro.view.ResultadoView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ResultadoService {

    private final ResultadoRepository resultadoRepository;

    private ResultadoDTO convertToDTO(ResultadoView resultadoView) {
        ResultadoDTO dto = new ResultadoDTO();
        dto.setId_doenca(resultadoView.getId_doenca());
        dto.setNm_doenca(resultadoView.getNm_doenca());
        dto.setTp_gravidade(resultadoView.getTp_gravidade());
        return dto;
    }

    private ResultadoView convertToEntity(ResultadoDTO dto) {
        ResultadoView resultadoView = new ResultadoView();
        resultadoView.setId_doenca(dto.getId_doenca());
        resultadoView.setNm_doenca(dto.getNm_doenca());
        return resultadoView;
    }

    public ResultadoDTO save(ResultadoDTO resultadoDTO) {
        ResultadoView resultadoView = convertToEntity(resultadoDTO);
        resultadoView = resultadoRepository.save(resultadoView);
        return convertToDTO(resultadoView);
    }

    public List<ResultadoDTO> getResultadoViews() {
        List<ResultadoDTO> resultadoViews = resultadoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return resultadoViews;
    }

    public void deletarPorId(UUID id) {
        resultadoRepository.deleteById(id);

    }

    public ResultadoDTO findById(UUID id) {
        Optional<ResultadoView> byId = resultadoRepository.findById(id);
        if (byId.isPresent()){
            return convertToDTO(byId.get());
        }
        throw new RuntimeException("id not found");
    }
}
