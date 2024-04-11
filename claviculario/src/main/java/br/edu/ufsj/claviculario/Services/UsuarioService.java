package br.edu.ufsj.claviculario.Services;

import br.edu.ufsj.claviculario.DTOs.UsuarioDTO;
import br.edu.ufsj.claviculario.Models.Usuario;
import br.edu.ufsj.claviculario.Repositories.UsuarioRepository;
import br.edu.ufsj.claviculario.util.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UsuarioService {
    
    private static final String MSG_SUCESSO = "Sucesso";
    
    private final UsuarioRepository usuarioRepository;
    
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    //C - CREATE
    public ResponseEntity<ResponseDTO<UsuarioDTO>> cadastrar(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioDTO.dtoToUser(usuarioDTO);
        this.usuarioRepository.save(usuario);
        
        return ResponseEntity.ok()
                .body(ResponseDTO
                        .<UsuarioDTO>builder()
                        .message(MSG_SUCESSO)
                        .detail(usuarioDTO)
                        .build());
    }
    
    //R - Read Nome
    public ResponseEntity<ResponseDTO<Usuario>> listarPeloNome (String nome) {
        Usuario usuario = this.usuarioRepository.findUsuarioByNome(nome);
        return ResponseEntity.ok()
                .body(ResponseDTO.<Usuario>builder()
                        .message(MSG_SUCESSO)
                        .detail(usuario)
                        .build());
    }
    
    //R - Read Matricula
    public ResponseEntity<ResponseDTO<Usuario>> listarPelaMatricula (String matricula) {
        Usuario usuario = this.usuarioRepository.findUsuarioByMatricula(matricula);
        return ResponseEntity.ok()
                .body(ResponseDTO.<Usuario>builder()
                        .message(MSG_SUCESSO)
                        .detail(usuario)
                        .build());
    }
    
    //R - Read All
    public ResponseEntity<ResponseDTO<Usuario>> listarTodos () {
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        return ResponseEntity.ok()
                .body(ResponseDTO.<Usuario>builder()
                        .message(MSG_SUCESSO)
                        .build());
    }
}
