package br.com.fiap.ProjetoSinistro.service;

import br.com.fiap.ProjetoSinistro.dto.TelefoneDTO;
import br.com.fiap.ProjetoSinistro.repositorios.TelefoneRepository;
import br.com.fiap.ProjetoSinistro.view.TelefoneView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TelefoneService {

    private final TelefoneRepository repository;

    private TelefoneDTO convertToDTO(TelefoneView telefoneView) {
        TelefoneDTO dto = new TelefoneDTO();
        dto.setId_telefone(telefoneView.getId_telefone());
        dto.setTp_contato(telefoneView.getTp_contato());
        dto.setNr_telefone(telefoneView.getNr_telefone());
        dto.setNr_ddd(telefoneView.getNr_ddd());
        return dto;
    }

    private TelefoneView convertToEntity(TelefoneDTO dto) {
        TelefoneView telefoneView = new TelefoneView();
        telefoneView.setId_telefone(dto.getId_telefone());
        telefoneView.setTp_contato(dto.getTp_contato());
        telefoneView.setNr_telefone(dto.getNr_telefone());
        return telefoneView;
    }

    public TelefoneDTO save(TelefoneDTO telefoneDTO) {
        TelefoneView telefoneView = convertToEntity(telefoneDTO);
        telefoneView = repository.save(telefoneView);
        return convertToDTO(telefoneView);
    }

    public List<TelefoneDTO> getTelefoneViews() {
        List<TelefoneDTO> telefoneViews = repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return telefoneViews;
    }

    public void deletarPorId(UUID id) {
        repository.deleteById(id);

    }

    public TelefoneDTO findById(UUID id) {
        Optional<TelefoneView> byId = repository.findById(id);
        if (byId.isPresent()){
            return convertToDTO(byId.get());
        }
        throw new RuntimeException("id not found");
    }

    private static TelefoneView toEntity(TelefoneDTO telefoneDTO) {
        TelefoneView telefoneView = new TelefoneView();
        telefoneView.setId_telefone(telefoneDTO.getId_telefone());
        telefoneView.setTp_contato(telefoneDTO.getTp_contato());
        telefoneView.setNr_telefone(telefoneDTO.getNr_telefone());
        telefoneView.setNr_ddd(telefoneDTO.getNr_ddd());
        return telefoneView;
    }

    private static TelefoneDTO toDto(TelefoneView telefoneView) {
        TelefoneDTO telefoneDTO = new TelefoneDTO();
        telefoneDTO.setId_telefone(telefoneView.getId_telefone());
        telefoneDTO.setTp_contato(telefoneView.getTp_contato());
        telefoneDTO.setNr_telefone(telefoneView.getNr_telefone());
        telefoneDTO.setNr_ddd(telefoneView.getNr_ddd());
        return telefoneDTO;
    }


}
