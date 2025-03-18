package br.com.fiap.ProjetoSinistro.service;

import br.com.fiap.ProjetoSinistro.dto.UsuarioDTO;
import br.com.fiap.ProjetoSinistro.view.UsuarioView;
import br.com.fiap.ProjetoSinistro.repositorios.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private UsuarioDTO convertToDTO(UsuarioView usuarioView) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId_usuario(usuarioView.getId_usuario());
        dto.setEmail(usuarioView.getEmail());
        //dto.setSenha(usuarioView.getSenha());
        return dto;
    }

    private UsuarioView convertToEntity(UsuarioDTO dto) {
        UsuarioView usuarioView = new UsuarioView();
        usuarioView.setId_usuario(dto.getId_usuario());
        usuarioView.setEmail(dto.getEmail());
        return usuarioView;
    }

    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        UsuarioView usuarioView = convertToEntity(usuarioDTO);
        usuarioView = usuarioRepository.save(usuarioView);
        return convertToDTO(usuarioView);

            }

    public List<UsuarioDTO> getUsuarioView() {
        List<UsuarioDTO> usuarioView = usuarioRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
        return usuarioView;

    }

    public void deletarPorId(UUID id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO findById(UUID id) {
        Optional<UsuarioView> byId = usuarioRepository.findById(id);
        if (byId.isPresent()){
            return convertToDTO(byId.get());
        }
        throw new RuntimeException("id não encontrado");
    }


}

































