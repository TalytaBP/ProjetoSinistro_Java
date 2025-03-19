package br.com.fiap.ProjetoSinistro.service;

import br.com.fiap.ProjetoSinistro.dto.EnderecoDTO;
import br.com.fiap.ProjetoSinistro.repositorios.EnderecoRepository;
import br.com.fiap.ProjetoSinistro.view.EnderecoView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoDTO salvar(EnderecoDTO enderecoDTO) {
        EnderecoView enderecoView = toEntity(enderecoDTO);
        if (enderecoDTO.getId_endereco() == null) {
            enderecoView = enderecoRepository.save(enderecoView);
        } else {
            EnderecoDTO byId = this.findById(enderecoDTO.getId_endereco());
            byId.setNr_cep(enderecoView.getNr_cep());
            byId.setNm_logradouro(enderecoView.getNm_logradouro());
            enderecoView = enderecoRepository.save(toEntity(byId));
        }
        return toDto(enderecoView);
    }

    public List<EnderecoDTO> findAll() {
        List<EnderecoView> list = enderecoRepository.findAll();
        List<EnderecoDTO> enderecoDTOList = list.stream().map(EnderecoService::toDto).toList();
        return enderecoDTOList;
    }

    public void deletarPorId(UUID id) {
        enderecoRepository.deleteById(id);

    }

    public EnderecoDTO findById(UUID id) {
        Optional<EnderecoView> byId = enderecoRepository.findById(id);
        if (byId.isPresent()){
            return toDto(byId.get());
        }
        throw new RuntimeException("uuid not found");
    }


    private static EnderecoView toEntity(EnderecoDTO enderecoDTO) {
        EnderecoView enderecoView = new EnderecoView();
        enderecoView.setId_endereco(enderecoDTO.getId_endereco());
        enderecoView.setNr_cep(enderecoDTO.getNr_cep());
        enderecoView.setNr_complemento(enderecoDTO.getNr_complemento());
        enderecoView.setNm_logradouro(enderecoDTO.getNm_logradouro());

        return enderecoView;
    }

    private static EnderecoDTO toDto(EnderecoView enderecoView) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId_endereco(enderecoView.getId_endereco());
        enderecoDTO.setNr_cep(enderecoView.getNr_cep());
        enderecoDTO.setNr_complemento(enderecoView.getNr_complemento());
        enderecoDTO.setNm_logradouro(enderecoView.getNm_logradouro());
        enderecoDTO.setNm_rua(enderecoView.getNm_rua());
        return enderecoDTO;
    }
}
