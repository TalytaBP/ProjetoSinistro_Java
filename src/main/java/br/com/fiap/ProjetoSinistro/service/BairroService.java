package br.com.fiap.ProjetoSinistro.service;

import br.com.fiap.ProjetoSinistro.dto.BairroDTO;
import br.com.fiap.ProjetoSinistro.repositorios.BairroRepository;
import br.com.fiap.ProjetoSinistro.view.BairroView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class BairroService {
    private final BairroRepository bairroRepository;

    private BairroDTO convertToDTO(BairroView bairroView) {
        BairroDTO dto = new BairroDTO();
        dto.setId_bairro(bairroView.getId_bairro());
        dto.setNm_bairro(bairroView.getNm_bairro());
        return dto;
    }

    private BairroView convertToEntity(BairroDTO dto) {
        BairroView bairroView = new BairroView();
        bairroView.setId_bairro(dto.getId_bairro());
        bairroView.setNm_bairro(dto.getNm_bairro());
        return bairroView;
    }

    public BairroDTO save(BairroDTO bairroDTO) {
        BairroView bairroView = convertToEntity(bairroDTO);
        bairroView = bairroRepository.save(bairroView);
        return convertToDTO(bairroView);
    }

    public List<BairroDTO> getBairroViews() {
        List<BairroDTO> bairroViews = bairroRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return bairroViews;
    }

    public void deletarPorId(UUID id) {
        bairroRepository.deleteById(id);

    }

    public BairroDTO findById(UUID id) {
        Optional<BairroView> byId = bairroRepository.findById(id);
        if (byId.isPresent()){
            return convertToDTO(byId.get());
        }
        throw new RuntimeException("id not found");
    }
}
